package com.exercise.movieapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.databinding.MoviesListItemBinding

class MoviesFavoriteAdapter:RecyclerView.Adapter<MoviesFavoriteAdapter.MoviesViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<MoviesFavorite>(){
        override fun areItemsTheSame(oldItem: MoviesFavorite, newItem: MoviesFavorite): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MoviesFavorite, newItem: MoviesFavorite): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MoviesListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return MoviesViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val Movies = differ.currentList[position]
        holder.bind(Movies)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MoviesViewHolder(
        val binding:MoviesListItemBinding):
        RecyclerView.ViewHolder(binding.root){
           fun bind(movies: MoviesFavorite){
               Log.i("MYTAG","came here ${movies.title}")
               binding.title.text = movies.title
               binding.description.text = movies.description
               binding.rating.text = movies.ratings

               Glide.with(binding.posterImg).
               load(movies.poster)
                   .into(binding.posterImg)

               binding.root.setOnClickListener {
                  onItemClickListener?.let {
                        it(movies)
                  }
               }
           }
        }

        private var onItemClickListener :((MoviesFavorite)->Unit)?=null

        fun setOnItemClickListener(listener : (MoviesFavorite)->Unit){
            onItemClickListener = listener
        }


}









