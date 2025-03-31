package com.example.lll

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    private lateinit var etSearch: EditText
    private lateinit var etYear: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnAddMovie: Button
    private lateinit var ivPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        etSearch = findViewById(R.id.etSearch)
        etYear = findViewById(R.id.etYear)
        btnSearch = findViewById(R.id.btnSearch)
        btnAddMovie = findViewById(R.id.btnAddMovie)
        ivPoster = findViewById(R.id.ivPoster)

        btnSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("searchQuery", etSearch.text.toString())
            startActivity(intent)
        }

        btnAddMovie.setOnClickListener {
            // Добавление фильма в базу данных
        }
    }
}
