package com.example.hap6_binar.presentation.ui.home

import androidx.lifecycle.*
import com.example.hap6_binar.domain.model.Movie
import com.example.hap6_binar.domain.usecase.GetMoviesUseCase
import com.example.hap6_binar.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<Result<List<Movie>>>()
    val movies: LiveData<Result<List<Movie>>> get() = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        _movies.value = Result.Loading
        viewModelScope.launch {
            try {
                val movies = getMoviesUseCase()
                _movies.value = Result.Success(movies)
            } catch (e: Exception) {
                _movies.value = Result.Failure(e)
            }
        }
    }
}
