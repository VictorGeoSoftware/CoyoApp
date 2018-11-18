package com.training.victor.development.di

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.mappers.CommentDataMapper
import com.training.victor.development.data.mappers.UserDataMapper
import com.training.victor.development.di.modules.DataManagerModule
import com.training.victor.development.network.CoyoRepository
import com.training.victor.development.network.ProfilesRepository

class TestDataManagerModule: DataManagerModule() {
    override fun provideDataManager(
        profileRepository: ProfilesRepository,
        coyoRepository: CoyoRepository,
        userDataMapper: UserDataMapper,
        commentDataMapper: CommentDataMapper
    ): DataManager {
        return super.provideDataManager(profileRepository, coyoRepository, userDataMapper, commentDataMapper)
//        return Mockito.mock(DataManager::class.java)
    }
}