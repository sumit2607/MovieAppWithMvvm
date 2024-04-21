package com.example.movieappwithmvvm.local.db

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappwithmvvm.local.response.ResponseModel
import com.example.movieappwithmvvm.local.response.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Dao
interface ResultModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultModel(resultModel: ResponseModel)

    @Query("SELECT * FROM app_database WHERE page = :page")
    suspend fun getResponseModelByPage(page: Int): ResponseModel?


    class LocalPagingSource(private val resultModelDao: ResultModelDao) :
        PagingSource<Int, ResultModel>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultModel> {
            return try {
                val page = params.key ?: 1 // Default to page 1 if key is null

                // Fetch data from Room database in the IO dispatcher
                val resultModels = withContext(Dispatchers.IO) {
                    resultModelDao.getResponseModelByPage(page)
                }

                // Determine prevKey and nextKey based on the current page
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (resultModels?.resultModels!!.isNotEmpty()) page + 1 else null

                // Return the loaded data as LoadResult.Page
                LoadResult.Page(
                    data = resultModels!!.resultModels,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } catch (e: Exception) {
                // Handle errors by returning LoadResult.Error
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, ResultModel>): Int? {
            TODO("Not yet implemented")
        }
    }
}
