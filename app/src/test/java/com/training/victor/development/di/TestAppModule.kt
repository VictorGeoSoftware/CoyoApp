package com.training.victor.development.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.training.victor.development.data.room.AppDataBase
import com.training.victor.development.di.modules.AppModule
import org.mockito.Mockito


class TestAppModule: AppModule(Mockito.mock(Application::class.java)) {
    override fun provideApplicationContext(): Context {
        return Mockito.mock(Context::class.java)
    }

    override fun provideAppDataBase(): AppDataBase {
        return Room.inMemoryDatabaseBuilder(Mockito.mock(Context::class.java), AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}