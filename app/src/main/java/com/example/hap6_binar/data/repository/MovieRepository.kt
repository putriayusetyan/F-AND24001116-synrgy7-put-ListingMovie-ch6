package com.example.hap6_binar.data.repository

import com.example.hap6_binar.data.api.MovieApiService
import com.example.hap6_binar.data.api.MovieResponse
import retrofit2.Response

class MovieRepository(private val apiService: MovieApiService) {
    suspend fun getPopularMovies(apiKey: String, page: Int): Response<MovieResponse> {
        return apiService.getPopularMovies(apiKey, page)
    }
}

