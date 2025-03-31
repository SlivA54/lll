package com.example.lll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchAdapter(private val context: Context, private var movies: List<MovieItem>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    fun updateMovies(newMovies: List<MovieItem>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: android.widget.TextView = itemView.findViewById(R.id.tvTitle)
        private val yearTextView: android.widget.TextView = itemView.findViewById(R.id.tvYear)
        private val genreTextView: android.widget.TextView = itemView.findViewById(R.id.tvGenre)
        private val posterImageView: android.widget.ImageView = itemView.findViewById(R.id.ivPoster)

        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            yearTextView.text = movie.year.toString()
            genreTextView.text = movie.genre // Если у вас есть поле жанра в модели Movie
            // Для загрузки постера можно использовать библиотеку Glide или Picasso
            // Пример с Glide:
            Glide.with(context)
                .load(movie.poster)
                .into(posterImageView)
        }
    }
}

