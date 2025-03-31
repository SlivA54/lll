package com.example.lll

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    private lateinit var searchQueryEditText: EditText
    private lateinit var yearEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var posterImageView: ImageView
    private lateinit var addMovieButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        searchQueryEditText = findViewById(R.id.search_query)
        yearEditText = findViewById(R.id.year)
        searchButton = findViewById(R.id.search_button)
        posterImageView = findViewById(R.id.poster)
        addMovieButton = findViewById(R.id.add_movie_button)

        searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("query", searchQueryEditText.text.toString())
            startActivity(intent)
        }

        addMovieButton.setOnClickListener {
            // Добавление фильма в БД
            val dbHelper = MovieDatabaseHelper(this)
            val movie = Movie(
                0, // id будет автоматически присвоен
                searchQueryEditText.text.toString(),
                yearEditText.text.toString().toInt(),
                "poster_url"
            )
            dbHelper.insertMovie(movie)
            finish()
        }
    }
}
