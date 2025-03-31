package com.example.lll

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private lateinit var fabAddMovie: FloatingActionButton
    private lateinit var btnDeleteSelected: Button
    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieDatabase.getDatabase(this).movieDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.moviesList)
        fabAddMovie = findViewById(R.id.fabAddMovie)
        btnDeleteSelected = findViewById(R.id.btnDeleteSelected)

        adapter = MovieAdapter { movie -> viewModel.deleteMovie(movie) } // Callback для удаления фильма
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fabAddMovie.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        btnDeleteSelected.setOnClickListener {
            // Удаление выбранных фильмов
            // Добавьте логику для удаления выбранных фильмов
        }

        // Наблюдаем за изменениями в базе данных
        viewModel.allMovies.observe(this, Observer { movies ->
            adapter.submitList(movies)
        })
    }
}
