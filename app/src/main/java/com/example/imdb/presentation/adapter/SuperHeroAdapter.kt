package com.example.imdb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Movie
import com.example.imdb.R

class SuperHeroAdapter(
    private var movies: List<Movie> = emptyList(),
    private var likedIds: Set<Long> = emptySet(),
    private val onToggleLike: (Movie) -> Unit
) : RecyclerView.Adapter<SuperHeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_superhero, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val movie = movies[position]
        val isLiked = likedIds.contains(movie.id)
        holder.render(movie, isLiked) {
            onToggleLike(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun updateData(newMovies: List<Movie>, newLikedIds: Set<Long>) {
        movies = newMovies
        likedIds = newLikedIds
        notifyDataSetChanged()
    }

}


