package com.example.movieappwithmvvm.local.db

import androidx.room.TypeConverter
import com.example.movieappwithmvvm.local.response.ResultModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromResultModelList(resultModels: List<ResultModel>?): String? {
        return gson.toJson(resultModels)
    }

    @TypeConverter
    fun toResultModelList(resultModelsString: String?): List<ResultModel>? {
        val type = object : TypeToken<List<ResultModel>>() {}.type
        return gson.fromJson(resultModelsString, type)
    }
}
