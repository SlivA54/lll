package com.example.lll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val onDeleteClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = emptyList()

    fun submitList(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onDeleteClick)
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val yearTextView: TextView = itemView.findViewById(R.id.yearTextView)
        private val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)

        fun bind(movie: Movie, onDeleteClick: (Movie) -> Unit) {
            titleTextView.text = movie.title
            yearTextView.text = movie.year.toString()
            // Для загрузки постера используйте Glide или Picasso (пример с Glide):
            // Glide.with(itemView.context).load(movie.poster).into(posterImageView)

            itemView.setOnLongClickListener {
                onDeleteClick(movie)
                true
            }
        }
    }
}
