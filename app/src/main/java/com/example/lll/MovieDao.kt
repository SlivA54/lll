package com.example.lll

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Delete
    suspend fun deleteMovie(movie: Movie)
}
