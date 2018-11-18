package com.training.victor.development.di.modules

import com.training.victor.development.BuildConfig
import com.training.victor.development.network.CoyoRepository
import com.training.victor.development.network.ProfilesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class NetworkModule {
    companion object {
        const val NAME_BASE_URL = "NAME_BASE_URL"
    }


    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString():String = BuildConfig.API_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, converter: Converter.Factory, callAdapterFactory: RxJava2CallAdapterFactory, @Named(
        NAME_BASE_URL
    ) baseUrl:String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addCallAdapterFactory(callAdapterFactory).addConverterFactory(converter).build()
    }


    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------- WEB SERVICES --------------------------------------------------------------------
    @Provides
    @Singleton
    open fun provideProfileRepository(retrofit: Retrofit) = retrofit.create(ProfilesRepository::class.java)!!

    @Provides
    @Singleton
    open fun provideCoyoRepository(retrofit: Retrofit) = retrofit.create(CoyoRepository::class.java)!!
}