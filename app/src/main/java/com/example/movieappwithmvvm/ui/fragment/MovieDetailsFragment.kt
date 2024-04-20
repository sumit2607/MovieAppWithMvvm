package com.example.movieappwithmvvm.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.movieappwithmvvm.R
import com.example.movieappwithmvvm.databinding.FragmentMovieDetailsBinding
import com.example.movieappwithmvvm.extra.Constants.MOVIE_URL
import com.example.movieappwithmvvm.local.response.ResultModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentMovieDetailsBinding
    lateinit var resultModel: ResultModel
    private var emptyList = emptyList<ResultModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  resultModel = arguments?.getSerializable("resultModel") as ResultModel

        if (savedInstanceState != null) {
            // Restore resultModel from savedInstanceState
            resultModel = savedInstanceState.getSerializable("resultModel") as ResultModel
        } else {
            // Get resultModel from arguments if savedInstanceState is null
            resultModel = arguments?.getSerializable("resultModel") as ResultModel
        }

        detailsBinding.apply {
            resultModel.apply {
                Glide.with(ivMovieThumbnail).load(MOVIE_URL + posterPath)
                    .into(ivMovieThumbnail)
                tvMovieName.text = originalTitle
                tvTime.text = releaseDate
                tvSynopsisData.text = overview
                starRatingBar.rating = voteAverage.toString().toFloat() / 2
                tvRatings.text = "Rating: ${voteAverage.toString()}"
                tvReview.text = "Popularity: ${popularity.toString()}"
                tvGenre1.text = "comedy"
                tvGenre2.text = "adventure"
                tvGenre3.text = "romance"
                vBack.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_movieDetailsFragment_to_homeFragment)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save resultModel to the savedInstanceState bundle
        outState.putSerializable("resultModel", resultModel)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            // Restore resultModel from savedInstanceState
            resultModel = it.getSerializable("resultModel") as ResultModel
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}