package com.example.imdb.presentation.view

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.presentation.adapter.TopMoviesAdapter
import com.example.imdb.presentation.viewmodel.HomeViewModel
import com.example.imdb.presentation.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var adapter: TopMoviesAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(requireContext())
        )[HomeViewModel::class.java]

        adapter = TopMoviesAdapter()
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerTopLiked)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.topMovies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        parentFragmentManager.setFragmentResultListener("likeChanged", viewLifecycleOwner) { _, _ ->
            viewModel.loadTopLikedMovies()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTopLikedMovies()
    }
}
