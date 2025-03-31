package com.example.lll

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String
    ): Call<MovieResponse>

    @GET("/")
    fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): Call<MovieDetailsResponse>
}
