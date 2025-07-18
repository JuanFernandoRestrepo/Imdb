package com.example.imdb.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.datasources.local.MovieLocalDataSourceImpl
import com.example.data.datasources.remote.MovieRemoteDataSourceImpl
import com.example.data.local.AppDatabase
import com.example.data.remote.ApiServices
import com.example.data.repository.LikeRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.imdb.R
import com.example.imdb.presentation.adapter.SuperHeroAdapter
import com.example.imdb.presentation.viewmodel.MovieViewModel
import com.example.imdb.presentation.viewmodel.MovieViewModelFactory
import com.example.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.launch
import com.example.data.local.SessionManager
import com.example.data.remote.ApiInterface
import com.example.data.remote.mapper.MovieMapper
import com.example.domain.usecase.IsMovieLikedUseCase
import com.example.domain.usecase.LikeMovieUseCase
import com.example.domain.usecase.UnlikeMovieUseCase

class RecyclerFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SuperHeroAdapter
    private lateinit var userEmail: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerSuperHero)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val sessionManager = SessionManager(requireContext())
        userEmail = sessionManager.getUserEmail() ?: ""

        val db = AppDatabase.getDatabase(requireContext())

        val apiService = ApiServices.getApi().create(ApiInterface::class.java)
        val remoteDataSource = MovieRemoteDataSourceImpl(apiService)

        val localDataSource = MovieLocalDataSourceImpl(db.likeDao())
        val mapper = MovieMapper()

        val movieRepository = MovieRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            mapper = mapper
        )

        val likeRepository = LikeRepositoryImpl(
            likeDao = db.likeDao(),
            movieDao = db.movieDao(),
            sessionManager = sessionManager
        )

        val viewModelFactory = MovieViewModelFactory(
            getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository),
            likeMovieUseCase = LikeMovieUseCase(likeRepository),
            unlikeMovieUseCase = UnlikeMovieUseCase(likeRepository),
            isMovieLikedUseCase = IsMovieLikedUseCase(likeRepository)
        )

        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]


        adapter = SuperHeroAdapter(emptyList(), emptySet()) { movie ->
            viewModel.toggleLike(movie, userEmail)
        }

        recyclerView.adapter = adapter

        observeData()
        viewModel.loadMovies()

        parentFragmentManager.setFragmentResultListener("likeChanged", viewLifecycleOwner) { _, _ ->
            viewModel.loadMovies()
        }
    }



    private fun observeData() {
        lifecycleScope.launch {
            viewModel.movies.collect { movieList ->
                val likedIds = viewModel.likedMovies.value
                adapter.updateData(movieList, likedIds)
            }
        }

        lifecycleScope.launch {
            viewModel.likedMovies.collect { likedIds ->
                val movieList = viewModel.movies.value
                adapter.updateData(movieList, likedIds)
            }
        }
    }
}
