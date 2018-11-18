package com.training.victor.development.di.components

import com.training.victor.development.di.modules.DataManagerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataManagerModule::class])
interface DataManagerComponent