package com.example.movieappwithmvvm.local.response


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "app_database")
data class ResponseModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val resultModels: List<ResultModel>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)