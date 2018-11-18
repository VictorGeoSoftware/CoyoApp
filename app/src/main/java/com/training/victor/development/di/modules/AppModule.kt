package com.training.victor.development.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.training.victor.development.data.room.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val application: Application) {

    @Provides
    @Singleton
    open fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    open fun provideAppDataBase(): AppDataBase = Room.databaseBuilder(application, AppDataBase::class.java, "app_db")
        .fallbackToDestructiveMigration()
        .build()
}