package com.example.lll

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie) // Return type is Unit by default

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Delete
    suspend fun deleteMovie(movie: Movie) // Return type is Unit by default
}
