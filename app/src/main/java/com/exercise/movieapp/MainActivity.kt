package com.exercise.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.exercise.movieapp.databinding.ActivityMainBinding
import com.exercise.movieapp.presentation.adapter.MoviesAdapter
import com.exercise.movieapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var viewModel: MoviesViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvMovies.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this)
            .get(MoviesViewModel::class.java)
    }
    fun hideBottomNav(hide: Boolean) {
        if(hide){
            binding.bnvMovies.visibility= View.INVISIBLE
        }else{
            binding.bnvMovies.visibility= View.VISIBLE

        }
    }
}