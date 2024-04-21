package com.example.movieappwithmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieappwithmvvm.local.response.ResultModel
import com.example.movieappwithmvvm.repositories.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appRepo: AppRepo) : ViewModel() {

    fun getMovieResponse(): Flow<PagingData<ResultModel>> {
        return appRepo.getPageList()
            .cachedIn(viewModelScope) // Ensure the data is cached in the ViewModel's scope
    }
    val liveData: LiveData<List<ResultModel>>
        get() = appRepo.products


    init {
        viewModelScope.launch {
            appRepo.getResponseFromAPI(1)
        }
    }

}