package com.training.victor.development.di

import android.app.Application
import android.content.Context
import com.training.victor.development.data.room.AppDataBase
import com.training.victor.development.di.modules.AppModule
import org.mockito.Mockito


class TestAppModule: AppModule(Mockito.mock(Application::class.java)) {
    override fun provideApplicationContext(): Context {
        return Mockito.mock(Context::class.java)
    }

    override fun provideAppDataBase(): AppDataBase {
        return Mockito.mock(AppDataBase::class.java)
    }
}