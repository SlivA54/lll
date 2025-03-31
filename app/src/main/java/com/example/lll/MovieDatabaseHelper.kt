package com.example.lll

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MovieDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "movies.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, year INTEGER, posterUrl TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Обработка обновления базы данных
    }

    fun insertMovie(movie: Movie) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put("title", movie.title)
            put("year", movie.year)
            put("posterUrl", movie.posterUrl)
        }
        db.insert("movies", null, contentValues)
        db.close()
    }

    fun getAllMovies(): List<Movie> {
        val db = readableDatabase
        val cursor = db.query("movies", null, null, null, null, null, null)
        val movies = mutableListOf<Movie>()
        while (cursor.moveToNext()) {
            movies.add(
                Movie(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
                )
            )
        }
        cursor.close()
        db.close()
        return movies
    }

    fun deleteMovie(id: Int) {
        val db = writableDatabase
        db.delete("movies", "id = ?", arrayOf(id.toString()))
        db.close()
    }
}
