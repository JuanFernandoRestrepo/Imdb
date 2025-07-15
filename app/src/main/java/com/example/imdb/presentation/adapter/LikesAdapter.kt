package com.example.imdb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.data.local.AppDatabase
import com.example.data.local.entity.MovieEntity
import com.example.imdb.R

class LikesAdapter(
    private val userEmail: String,
    private val db: AppDatabase,
    private val onLikeChanged: () -> Unit
) : ListAdapter<MovieEntity, MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_superhero, parent, false)
        return MovieViewHolder(view, userEmail, db, onLikeChanged)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }
    }
}
