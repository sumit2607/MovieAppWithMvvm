package com.example.movieappwithmvvm.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.movieappwithmvvm.extra.Constants.API_KEY
import com.example.movieappwithmvvm.local.APIClient
import com.example.movieappwithmvvm.local.Resource
import com.example.movieappwithmvvm.local.ResponseHandler
import com.example.movieappwithmvvm.local.db.AppDatabase
import com.example.movieappwithmvvm.local.response.ResponseModel
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val myDao: AppDatabase,
    private val apiClient: APIClient,
   private val applicationContext: Context
) {
    private val responseHandler = ResponseHandler()


    fun getPageList() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            AppPagingSource(apiClient)
        }
    ).liveData

    suspend fun getAllDataFromDB(): ResponseModel? {
        return myDao.resultModelDao().getResponseModelByPage(1)
    }




    suspend fun getResponseFromAPI(page: Int): Resource<ResponseModel> {
        val response = apiClient.getResponseFromAPI(API_KEY, page)


        return try {
            if (isInternetAvailable(applicationContext)) {
                Log.d("TAG", "getResponseFromAPI: " + "here in line no 47")
                myDao.resultModelDao().insertResultModel(response)
                responseHandler.handleSuccess(response)
            } else {
                // No internet connection
                Log.d("TAG", "getResponseFromAPI: " + "here in line no 50")
                getAllDataFromDB()
                responseHandler.handleSuccess(getAllDataFromDB())
            }

        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnected ?: false
        }
    }
}