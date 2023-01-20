package com.exercise.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.exercise.movieapp.data.model.MoviesFavorite
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.databinding.FragmentMoviesInfoBinding
import com.exercise.movieapp.presentation.viewmodel.MoviesViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBinding: FragmentMoviesInfoBinding
    private lateinit var viewModel : MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentMoviesInfoBinding.bind(view)
        val args : InfoFragmentArgs by navArgs()
        val movies = args.selectedMovies

        viewModel=(activity as MainActivity).viewModel
        (activity as MainActivity).hideBottomNav(true)
        fragmentInfoBinding.title.text = movies.title
        fragmentInfoBinding.description.text = movies.description
        if(movies.adult!!){
            fragmentInfoBinding.rating.text = "18+"
        }else{
            fragmentInfoBinding.rating.text = "PG"
        }
        fragmentInfoBinding.fabSave.setOnClickListener {
            val datas=MoviesFavorite( movies.id, movies.title, movies.poster,
                    movies.adult,movies.description)
            viewModel.saveFavoriteMovies(datas)
            Snackbar.make(view,"Saved Successfully!",Snackbar.LENGTH_LONG).show()
        }
        fragmentInfoBinding.fabHide.setOnClickListener {
            val datas=MoviesHide( movies.id, movies.title, movies.poster,
                movies.adult,movies.description)
            viewModel.saveMoviesHide(datas)
            Snackbar.make(view,"Hidden Successfully!",Snackbar.LENGTH_LONG).show()
        }
    }
}







