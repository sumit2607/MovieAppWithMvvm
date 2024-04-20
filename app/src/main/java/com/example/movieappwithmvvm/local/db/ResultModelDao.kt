package com.example.movieappwithmvvm.local.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappwithmvvm.local.response.ResponseModel
import com.example.movieappwithmvvm.local.response.ResultModel

@Dao
interface ResultModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultModel(resultModel: ResponseModel)

    @Query("SELECT * FROM result_models")
    fun getAllResultModels(): PagingSource<Int, ResultModelRoom>
}
