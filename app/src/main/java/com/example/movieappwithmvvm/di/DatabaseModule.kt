package com.example.movieappwithmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.movieappwithmvvm.local.db.AppDatabase
import com.example.movieappwithmvvm.local.db.ResultModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideResultModelDao(appDatabase: AppDatabase): ResultModelDao {
        return appDatabase.resultModelDao()
    }


}