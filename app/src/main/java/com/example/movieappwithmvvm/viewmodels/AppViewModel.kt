package com.example.movieappwithmvvm.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieappwithmvvm.local.Resource
import com.example.movieappwithmvvm.local.response.ResponseModel
import com.example.movieappwithmvvm.repositories.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appRepo: AppRepo) : ViewModel() {

    fun getMovieResponse() = appRepo.getPageList()


    fun getResponseFromAPI(page: Int): LiveData<Resource<ResponseModel>> {
        return liveData(Dispatchers.Main) {
            val response = appRepo.getResponseFromAPI(page)
            Log.d("TAG", "getResponseFromAPI: " + "here in line no 23 " + response)
            emit(response)
        }

    }


}