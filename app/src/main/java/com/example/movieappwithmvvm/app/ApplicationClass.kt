package com.example.movieappwithmvvm.app

import android.app.Application
import com.example.movieappwithmvvm.di.AppModule
import com.example.movieappwithmvvm.local.db.AppDatabase
import com.example.movieappwithmvvm.repositories.AppRepo
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ApplicationClass : Application() {
//    private lateinit var myRepo : AppRepo


    override fun onCreate() {
        super.onCreate()
//       // AppModule.provideApplicationContext(applicationContext)
//        val apiInterface = AppModule.provideApiService()
//        val dataBase = AppDatabase.getInstance(applicationContext)
//        myRepo = AppRepo(dataBase, apiInterface, applicationContext)
    }
}