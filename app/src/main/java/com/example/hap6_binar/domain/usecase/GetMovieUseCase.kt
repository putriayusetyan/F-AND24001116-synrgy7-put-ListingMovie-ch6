package com.example.hap6_binar.domain.usecase

import com.example.hap6_binar.data.repository.MovieRepository
import com.example.hap6_binar.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getMovies()
    }
}
