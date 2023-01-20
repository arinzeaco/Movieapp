package com.exercise.movieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercise.movieapp.data.model.MoviesHide
import com.exercise.movieapp.databinding.FragmentMoviesBinding
import com.exercise.movieapp.presentation.adapter.MoviesAdapter
import com.exercise.movieapp.presentation.viewmodel.MoviesViewModel
import kotlinx.coroutines.*


class MoviesFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var ids:List<MoviesHide>
    private lateinit var moviesList:List<Int>
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
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
        if (viewModel.getSavedMoviesHide().value?.isNotEmpty() == true) {
            ids = viewModel.getSavedMoviesHide().value!!

            val moviesList = ids.map { it.id }.toList()
            viewModel.getSavedMovies(moviesList as List<Int>).observe(viewLifecycleOwner) {
                moviesAdapter.differ.submitList(it)
            }
        }else{
            viewModel.getSavedMovies().observe(viewLifecycleOwner) {
                moviesAdapter.differ.submitList(it)
            }
        }


        (activity as MainActivity).hideBottomNav(false)

    }

    private fun initRecyclerView() {
        fragmentMoviesBinding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar() {
        if (viewModel.progressBar.value!!) {
            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMoviesBinding.progressBar.visibility = View.INVISIBLE
        }
    }


    //search
    private fun setSearchView() {
        fragmentMoviesBinding.svMovies.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    viewModel.searchMovies(p0.toString())
                    viewSearchedMovies()
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {

                    viewModel.searchMovies(p0.toString())
                    viewSearchedMovies()

                    return false
                }

            })

        fragmentMoviesBinding.svMovies.setOnCloseListener {
            initRecyclerView()
            false
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
        if (viewModel.getSavedMoviesHide().value?.isNotEmpty() == true) {
            ids = viewModel.getSavedMoviesHide().value!!

            val moviesList = ids.map { it.id }.toList()
            viewModel.getSavedMovies(moviesList as List<Int>).observe(viewLifecycleOwner) {
                moviesAdapter.differ.submitList(it)
            }
        }else{
            viewModel.getSavedMovies().observe(viewLifecycleOwner) {
                moviesAdapter.differ.submitList(it)
            }
        }
    }

    fun viewSearchedMovies() {
        viewModel.searchedMovies.observe(viewLifecycleOwner, { response ->
            when (response) {
                is com.exercise.movieapp.data.util.Resource.Success -> {

                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.Movies.toList().size}")
                        moviesAdapter.differ.submitList(it.Movies.toList())

                    }
                }
                is com.exercise.movieapp.data.util.Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is com.exercise.movieapp.data.util.Resource.Loading -> {
                    showProgressBar()
                }

            }
        })
    }

}
















