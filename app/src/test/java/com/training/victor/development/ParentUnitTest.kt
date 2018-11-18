package com.training.victor.development

import com.training.victor.development.di.TestAppModule
import com.training.victor.development.di.TestDataManagerModule
import com.training.victor.development.di.TestNetworkModule
import com.training.victor.development.di.components.AppComponent
import com.training.victor.development.di.modules.AppModule
import com.training.victor.development.di.modules.DataManagerModule
import com.training.victor.development.di.modules.NetworkModule
import dagger.Component
import org.junit.Before
import javax.inject.Singleton

abstract class ParentUnitTest {
    open lateinit var testNetworkComponent: TestAppComponent


    @Singleton
    @Component(modules = [AppModule::class, NetworkModule::class, DataManagerModule::class])
    interface TestAppComponent : AppComponent {
        fun inject(target: CoyoPresenterTest)
    }

    @Before
    open fun setUp() {
        testNetworkComponent = DaggerParentUnitTest_TestAppComponent.builder()
            .appModule(TestAppModule())
            .networkModule(TestNetworkModule())
            .dataManagerModule(TestDataManagerModule())
            .build()
    }

    protected abstract fun <T> createMockedPresenter(): T
}