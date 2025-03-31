package com.example.lll

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var etSearch: EditText
    private lateinit var etYear: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnAddMovie: Button
    private lateinit var ivPoster: ImageView
    private lateinit var movieDatabase: MovieDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        etSearch = findViewById(R.id.etSearch)
        etYear = findViewById(R.id.etYear)
        btnSearch = findViewById(R.id.btnSearch)
        btnAddMovie = findViewById(R.id.btnAddMovie)
        ivPoster = findViewById(R.id.ivPoster)

        movieDatabase = MovieDatabase.getDatabase(this)

        btnSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("searchQuery", etSearch.text.toString())
            startActivity(intent)
        }

        btnAddMovie.setOnClickListener {
            val title = etSearch.text.toString()
            val year = etYear.text.toString()
            val posterUrl = "https://example.com/poster.jpg" // Замените на реальный URL

            lifecycleScope.launch {
                val movie = Movie(0, title, year, posterUrl)
                movieDatabase.movieDao().insertMovie(movie)
                finish() // Закрыть AddActivity после добавления фильма.
            }
        }

    }
}

