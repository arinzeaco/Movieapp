package com.exercise.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.movieapp.data.model.Movies
import com.exercise.movieapp.databinding.FragmentMoviesBinding
import com.exercise.movieapp.presentation.adapter.MoviesAdapter
import com.exercise.movieapp.presentation.viewmodel.MoviesViewModel


class MoviesFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
     lateinit var moviesList:List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMoviesBinding = FragmentMoviesBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        moviesAdapter = (activity as MainActivity).moviesAdapter
        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_Movies", it)
            }
            findNavController().navigate(
                R.id.action_MoviesFragment_to_infoFragment,
                bundle
            )
        }
        initRecyclerView()
        setSearchView()
        viewModel.getMovies()
        viewModel.getSavedMovies().observe(viewLifecycleOwner) {
            displayEmptyText(it)
            moviesAdapter.differ.submitList(it)
        }

        (activity as MainActivity).hideBottomNav(false)
        showProgressBar()
    }

    private fun initRecyclerView() {
        fragmentMoviesBinding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar() {
        viewModel.progressBar.observe(viewLifecycleOwner) {

            if (it!!) {
                fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            } else {
                fragmentMoviesBinding.progressBar.visibility = View.INVISIBLE
            }
        }
    }


    //search
    private fun setSearchView() {
        fragmentMoviesBinding.svMovies.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    viewSearchedMovies(p0.toString())
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {

//                    viewModel.searchMovies(p0.toString())
                    viewSearchedMovies(p0.toString())

                    return false
                }

            })

        fragmentMoviesBinding.svMovies.setOnCloseListener {
            initRecyclerView()
            false
        }
    }


    fun viewSearchedMovies(search: String) {

        viewModel.getSavedMoviesHide()
        if (viewModel.moviesHide.value?.isNotEmpty() == true) {
            viewModel.moviesHide.observe(viewLifecycleOwner) {

                moviesList = viewModel.moviesHide.value!!.map {
                    it.idd.toString()
                }.toList()
                viewModel.getSavedMovies(search, moviesList)

            }
            viewModel.moviesFilter.observe(viewLifecycleOwner) {
                displayEmptyText(it)
                moviesAdapter.differ.submitList(it)
            }

        } else {
            viewModel.getSavedMovies(search, listOf())

            viewModel.moviesFilter.observe(viewLifecycleOwner) {
                displayEmptyText(it)
                moviesAdapter.differ.submitList(it)
            }
        }
    }

    fun displayEmptyText(movies: List<Movies>) {
        if (movies.isNotEmpty()){
            fragmentMoviesBinding.empty.visibility=View.GONE
        }else{
            fragmentMoviesBinding.empty.visibility=View.VISIBLE

        }
    }
}
















