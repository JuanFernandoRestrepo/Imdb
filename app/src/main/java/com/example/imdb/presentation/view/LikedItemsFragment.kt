package com.example.imdb.presentation.view

import com.example.imdb.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.local.AppDatabase
import com.example.imdb.presentation.adapter.LikesAdapter
import kotlinx.coroutines.launch


class LikedItemsFragment : Fragment() {

    private lateinit var adapter: LikesAdapter
    private lateinit var db: AppDatabase
    private lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_liked_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDatabase(requireContext())

        email = requireContext()
            .getSharedPreferences("user_session", Context.MODE_PRIVATE)
            .getString("email", "") ?: ""

        adapter = LikesAdapter(email, db) {
            reloadLikes()
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerLiked)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        reloadLikes()
    }

    private fun reloadLikes() {
        viewLifecycleOwner.lifecycleScope.launch {
            val likeDao = db.likeDao()
            val movieDao = db.movieDao()

            val likes = likeDao.getLikesByUser(email)
            val likedIds = likes.map { it.itemId }


            val likedMovies = if (likedIds.isNotEmpty()) {
                movieDao.getLikedMovies(likedIds)
            } else {
                emptyList()
            }
            likedMovies.forEach {
                Log.d("LikedFragment", "Movie -> id: ${it.id}, title: ${it.original_title}")
            }

            adapter.submitList(likedMovies)
        }
    }

}
