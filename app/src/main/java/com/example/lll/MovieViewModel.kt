package com.example.lll

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel(private val movieDao: MovieDao) : ViewModel() {
    val allMovies: LiveData<List<Movie>> = movieDao.getAllMovies()

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            movieDao.deleteMovie(movie)
        }
    }
}
