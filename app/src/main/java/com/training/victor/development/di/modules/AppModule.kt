package com.training.victor.development.di.modules

import android.app.Application
import android.content.Context
import com.training.victor.development.data.mappers.CommentDataMapper
import com.training.victor.development.data.mappers.UserDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application
}