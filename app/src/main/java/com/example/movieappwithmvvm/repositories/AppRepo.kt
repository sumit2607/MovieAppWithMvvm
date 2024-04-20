package com.example.movieappwithmvvm.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.movieappwithmvvm.extra.Constants.API_KEY
import com.example.movieappwithmvvm.local.APIClient
import com.example.movieappwithmvvm.local.Resource
import com.example.movieappwithmvvm.local.ResponseHandler
import com.example.movieappwithmvvm.local.response.ResponseModel
import javax.inject.Inject

class AppRepo @Inject constructor(private val apiClient: APIClient) {

    private val responseHandler = ResponseHandler()

    fun getPageList() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            AppPagingSource(apiClient)
        }
    ).liveData

    suspend fun getResponseFromAPI(page: Int): Resource<ResponseModel> {
        val response = apiClient.getResponseFromAPI(API_KEY, page)
        return try {
            responseHandler.handleSuccess(response)
        }catch (e: Exception){
            responseHandler.handleException(e)
        }
    }
}