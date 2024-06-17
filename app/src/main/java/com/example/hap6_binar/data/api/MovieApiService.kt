package com.example.hap6_binar.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.hap6_binar.data.api.MovieResponse as ApiMovieResponse

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<ApiMovieResponse>
}
