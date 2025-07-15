package com.example.imdb.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.data.remote.ApiServices.Companion.IMAGE_URL
import com.example.domain.model.Movie
import com.example.imdb.R

class SuperHeroViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val title = view.findViewById<TextView>(R.id.textView)
    private val marca = view.findViewById<TextView>(R.id.textView2)
    private val overview = view.findViewById<TextView>(R.id.textView3)
    private val image = view.findViewById<ImageView>(R.id.posterMovie)
    private val likeButton = view.findViewById<LottieAnimationView>(R.id.like_image_view)

    fun render(movie: Movie, isLiked: Boolean, onLikeClick: () -> Unit) {
        title.text = movie.title
        marca.text= movie.id.toString()
        overview.text = movie.description
        Glide.with(image.context).load(IMAGE_URL + movie.imageUrl).into(image)

        if (isLiked) {
            likeButton.setAnimation(R.raw.bandai_dokkan)
            likeButton.progress = 1f
        } else {
            likeButton.setImageResource(R.drawable.like)
        }

        likeButton.setOnClickListener {
            if (!isLiked) {
                likeButton.setAnimation(R.raw.bandai_dokkan)
                likeButton.playAnimation()
            } else {
                likeButton.setImageResource(R.drawable.like)
            }
            onLikeClick()
        }
    }


}
