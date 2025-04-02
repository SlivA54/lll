package com.example.lll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private val onDeleteClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = emptyList()
    private val selectedMovies = mutableSetOf<Movie>() // Stores selected movies

    fun submitList(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    fun getSelectedMovies(): List<Movie> = selectedMovies.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, selectedMovies.contains(movie)) { isChecked ->
            if (isChecked) {
                selectedMovies.add(movie)
            } else {
                selectedMovies.remove(movie)
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val yearTextView: TextView = itemView.findViewById(R.id.yearTextView)
        private val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(movie: Movie, isSelected: Boolean, onCheckedChange: (Boolean) -> Unit) {
            titleTextView.text = movie.title
            yearTextView.text = movie.year.toString()
            checkBox.isChecked = isSelected

            // Handle CheckBox selection
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChange(isChecked)
            }

        }
    }
}
