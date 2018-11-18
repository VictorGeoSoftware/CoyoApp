package com.training.victor.development.di.modules

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.mappers.CommentDataMapper
import com.training.victor.development.data.mappers.PostDataMapper
import com.training.victor.development.data.mappers.UserDataMapper
import com.training.victor.development.network.CoyoRepository
import com.training.victor.development.network.ProfilesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataManagerModule {

    @Provides
    @Singleton
    fun postDataMapper(): PostDataMapper = PostDataMapper()

    @Provides
    @Singleton
    fun provideUserDataMapper(): UserDataMapper = UserDataMapper()

    @Provides
    @Singleton
    fun provideCommentDataMapper(): CommentDataMapper = CommentDataMapper()

    @Provides
    @Singleton
    open fun provideDataManager(profileRepository: ProfilesRepository,
                                coyoRepository: CoyoRepository,
                                postDataMapper: PostDataMapper,
                                userDataMapper: UserDataMapper,
                                commentDataMapper: CommentDataMapper):
            DataManager = DataManager(profileRepository, coyoRepository, postDataMapper, userDataMapper, commentDataMapper)
}