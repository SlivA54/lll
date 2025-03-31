package com.example.lll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.widget.TextView
import android.widget.ImageView

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
        private val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
        private val yearTextView: TextView = itemView.findViewById(R.id.tvYear)
        private val genreTextView: TextView = itemView.findViewById(R.id.tvGenre)
        private val posterImageView: ImageView = itemView.findViewById(R.id.ivPoster)

        fun bind(movie: MovieItem) { // Изменено на MovieItem
            titleTextView.text = movie.Title
            yearTextView.text = movie.Year
            // genreTextView.text = movie.genre // Нет поля genre в MovieItem
            Glide.with(context)
                .load(movie.Poster)
                .into(posterImageView)
        }
    }
}
