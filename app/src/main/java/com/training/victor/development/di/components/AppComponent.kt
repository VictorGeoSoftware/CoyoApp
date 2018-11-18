package com.training.victor.development.di.components

import android.app.Application
import com.training.victor.development.di.modules.AppModule
import com.training.victor.development.di.modules.DataManagerModule
import com.training.victor.development.di.modules.NetworkModule
import com.training.victor.development.di.modules.PresenterModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataManagerModule::class])
interface AppComponent {
    fun inject(application: Application)
    fun plus(presenterModule: PresenterModule): PresenterComponent
}