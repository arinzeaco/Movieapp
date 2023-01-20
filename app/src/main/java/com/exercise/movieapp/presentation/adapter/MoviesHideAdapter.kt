package com.exercise.movieapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.databinding.MoviesListItemBinding

class MoviesHideAdapter:RecyclerView.Adapter<MoviesHideAdapter.MoviesViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<MoviesHide>(){
        override fun areItemsTheSame(oldItem: MoviesHide, newItem: MoviesHide): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MoviesHide, newItem: MoviesHide): Boolean {
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
           fun bind(movies: MoviesHide){
               Log.i("MYTAG","came here ${movies.title}")
               binding.title.text = movies.title
               binding.description.text = movies.description
               if(movies.adult!!){
                   binding.rating.text = "18+"
               }else{
                   binding.rating.text = "PG"
               }

//               Glide.with(binding.ivMoviesImage.context).
//               load(Movies.urlToImage).
//               into(binding.ivMoviesImage)

               binding.root.setOnClickListener {
                  onItemClickListener?.let {
                        it(movies)
                  }
               }
           }
        }

        private var onItemClickListener :((MoviesHide)->Unit)?=null

        fun setOnItemClickListener(listener : (MoviesHide)->Unit){
            onItemClickListener = listener
        }


}









