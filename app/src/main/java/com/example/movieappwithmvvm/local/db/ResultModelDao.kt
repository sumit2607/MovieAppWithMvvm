package com.example.movieappwithmvvm.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappwithmvvm.local.response.ResponseModel

@Dao
interface ResultModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultModel(resultModel: ResponseModel)

    @Query("SELECT * FROM app_database WHERE page = :page")
    suspend fun getResponseModelByPage(page: Int): ResponseModel?
}
