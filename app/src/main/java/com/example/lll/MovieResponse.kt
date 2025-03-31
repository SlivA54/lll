package com.example.lll

data class MovieResponse(
    val Search: List<MovieItem>,
    val totalResults: String
)