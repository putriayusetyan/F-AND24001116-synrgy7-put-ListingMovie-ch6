package com.example.hap6_binar.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hap6_binar.data.repository.MovieRepository
import com.example.hap6_binar.data.api.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun fetchPopularMovies(apiKey: String, page: Int) {
        viewModelScope.launch {
            val response = repository.getPopularMovies(apiKey, page)
            if (response.isSuccessful) {
                _movies.postValue(response.body()?.results)
            }
        }
    }
}
