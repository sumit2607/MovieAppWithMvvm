package com.example.movieappwithmvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieappwithmvvm.R
import com.example.movieappwithmvvm.databinding.FragmentHomeBinding
import com.example.movieappwithmvvm.local.response.OnCardClick
import com.example.movieappwithmvvm.local.response.ResultModel
import com.example.movieappwithmvvm.ui.adapter.MovieAdapter
import com.example.movieappwithmvvm.ui.adapter.NewMovieAdapter
import com.example.movieappwithmvvm.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), OnCardClick {
    // Inject ResultModelDao using field injection
    val viewModel: AppViewModel by viewModels()
    private lateinit var homeBinding: FragmentHomeBinding
    lateinit var movieAdapter: MovieAdapter
    private var emptyList = emptyList<ResultModel>()
    private var resultList: List<ResultModel> =
        emptyList() // Global variable to hold the list of ResultModel items


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Restore myData from savedInstanceState if available

        savedInstanceState?.let {
            emptyList = it.getSerializable("data") as ArrayList<ResultModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

        // Observe the PagingData<ResultModel> from the ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovieResponse().collect { pagingData ->
                // Submit the paging data to the adapter
                movieAdapter.submitData(pagingData)
            }
        }

        if (savedInstanceState == null) {
            // No saved instance state, make the API call
            Log.d("TAG", "onViewCreated: " + "here in line no 82")
            viewModel.liveData.observe(viewLifecycleOwner, Observer {
                emptyList = it
                Log.d("TAG", "onViewCreated: " + " here in line no 83" + emptyList)
                val adaptor = NewMovieAdapter(emptyList, this)
                homeBinding.rvTopMovies.adapter = adaptor
            })


        } else {
            // Use data from saved instance state
            Log.d("TAG", "onViewCreated: " + "here in line no 101")
            emptyList =
                savedInstanceState.getSerializable("data") as? List<ResultModel> ?: emptyList
            val adaptor = NewMovieAdapter(emptyList, this)
            homeBinding.rvTopMovies.adapter = adaptor
        }


    }

    private fun setAdapter() {
        movieAdapter = MovieAdapter(this)
        homeBinding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }


    override fun onCardClicked(resultModel: ResultModel) {
        val bundle = Bundle()
        bundle.putSerializable("resultModel", resultModel)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("data", emptyList as ArrayList)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            emptyList = it.getSerializable("data") as ArrayList<ResultModel>

            // Now you can use myData after restoring
        }

    }


}