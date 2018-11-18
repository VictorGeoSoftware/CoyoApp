package com.training.victor.development

import android.app.Application
import com.training.victor.development.di.components.AppComponent
import com.training.victor.development.di.components.DaggerAppComponent
import com.training.victor.development.di.components.PresenterComponent
import com.training.victor.development.di.modules.AppModule
import com.training.victor.development.di.modules.PresenterModule

class MainApplication: Application() {
    private val component: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this) ).build() }

    private var presenterComponent: PresenterComponent? = null


    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    fun createPresenterComponent(): PresenterComponent {
        return component.plus(PresenterModule())
    }

    fun releasePresenterComponent() {
        presenterComponent = null
    }
}