package com.exercise.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.exercise.movieapp.databinding.FragmentMoviesInfoBinding

class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBinding: FragmentMoviesInfoBinding

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


//        fragmentInfoBinding.wvInfo.apply {
//            webViewClient = WebViewClient()
//            if(movies.url!=null) {
//                loadUrl(movies.url)
//            }
//
//        }

    }
}







