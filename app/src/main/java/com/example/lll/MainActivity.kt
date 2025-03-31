package com.example.lll

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter
    private lateinit var dbHelper: MovieDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.movies_list)
        adapter = MoviesAdapter { movie, isChecked ->
            // Обработка выбора фильма для удаления
        }
        recyclerView.adapter = adapter

        dbHelper = MovieDatabaseHelper(this)
        dbHelper.createTable()

        val movies = dbHelper.getAllMovies()
        if (movies.isEmpty()) {
            recyclerView.visibility = View.GONE
            findViewById<ImageView>(R.id.empty_frame).visibility = View.VISIBLE
            findViewById<TextView>(R.id.no_movies_text).visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            findViewById<ImageView>(R.id.empty_frame).visibility = View.GONE
            findViewById<TextView>(R.id.no_movies_text).visibility = View.GONE
            adapter.submitList(movies)
        }

        findViewById<FloatingActionButton>(R.id.fab_add_movie).setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        findViewById<Toolbar>(R.id.toolbar).findViewById<AppCompatImageButton>(R.id.btn_delete_selected).setOnClickListener {
            // Удаление выбранных фильмов
        }
    }
}
