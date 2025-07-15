package com.example.imdb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.imdb.R
import com.example.myapplication.data.MovieWithLikes

class TopMoviesAdapter : ListAdapter<MovieWithLikes, TopMovieViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top, parent, false)
        return TopMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MovieWithLikes>() {
        override fun areItemsTheSame(oldItem: MovieWithLikes, newItem: MovieWithLikes) =
            oldItem.movie.id == newItem.movie.id

        override fun areContentsTheSame(oldItem: MovieWithLikes, newItem: MovieWithLikes) =
            oldItem == newItem
    }
}

