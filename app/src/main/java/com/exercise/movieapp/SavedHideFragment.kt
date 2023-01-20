package com.exercise.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercise.movieapp.databinding.FragmentSavedBinding
import com.exercise.movieapp.presentation.adapter.MoviesHideAdapter
import com.exercise.movieapp.presentation.viewmodel.MoviesViewModel
import com.google.android.material.snackbar.Snackbar


class SavedHideFragment : Fragment() {
    private lateinit var fragmentSavedBinding: FragmentSavedBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesHideAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSavedBinding = FragmentSavedBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        moviesAdapter = MoviesHideAdapter()
        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_Movies", it)
            }
            (activity as MainActivity).hideBottomNav(false)

            findNavController().navigate(
                R.id.action_savedFragment_to_infoFragment,
                bundle
            )
        }
        initRecyclerView()
        viewModel.getSavedMoviesHide().observe(viewLifecycleOwner) {
            moviesAdapter.differ.submitList(it)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val Movies = moviesAdapter.differ.currentList[position]
                viewModel.deleteMoviesHide(Movies)
                Snackbar.make(view, "Hide Movies Deleted Successfully", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo") {
                            viewModel.saveMoviesHide(Movies)
                        }
                        show()
                    }

            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(fragmentSavedBinding.rvSaved)
        }

    }


    private fun initRecyclerView() {
        fragmentSavedBinding.rvSaved.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}