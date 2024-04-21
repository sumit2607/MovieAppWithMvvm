package com.example.movieappwithmvvm.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieappwithmvvm.extra.Constants.API_KEY
import com.example.movieappwithmvvm.local.APIClient
import com.example.movieappwithmvvm.local.db.AppDatabase
import com.example.movieappwithmvvm.local.db.ResultModelDao
import com.example.movieappwithmvvm.local.response.ResponseModel
import com.example.movieappwithmvvm.local.response.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val myDao: AppDatabase,
    private val apiClient: APIClient,
    private val applicationContext: Context
) {

    private val _products = MutableLiveData<List<ResultModel>>()
    val products: LiveData<List<ResultModel>>
        get() = _products

    fun getPageList(): Flow<PagingData<ResultModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                if (isInternetAvailable(applicationContext)) {
                    AppPagingSource(apiClient)
                } else {
                    ResultModelDao.LocalPagingSource(myDao.resultModelDao())
                }
            }
        ).flow
    }


    suspend fun getAllDataFromDB(): ResponseModel? {
        return myDao.resultModelDao().getResponseModelByPage(1)
    }


    suspend fun getResponseFromAPI(page: Int) {
        if (isInternetAvailable(applicationContext)) {
            try {
                val response = apiClient.getResponseFromAPI(API_KEY, page)
                myDao.resultModelDao().insertResultModel(response)
                _products.postValue(response.resultModels)
            } catch (e: Exception) {
                Log.e("AppRepo", "Failed to fetch data from API: ${e.message}")
                // Handle the error here, e.g., notify UI or retry later
            }
        } else {
            Log.d("AppRepo", "No internet connection. Fetching data from DB...")
            withContext(Dispatchers.IO) {
                try {
                    val localData = getAllDataFromDB()
                    _products.postValue(localData?.resultModels)
                    Log.d("AppRepo", "No internet connection. Fetching data from DB... 75")
                    // Now you can handle or use the localData fetched from Room
                } catch (e: Exception) {
                    Log.e("AppRepo", "Failed to fetch local data from Room: ${e.message}")
                    // Handle the error, e.g., show error message or fallback to default data
                }
            }
        }
    }

}


private fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}

