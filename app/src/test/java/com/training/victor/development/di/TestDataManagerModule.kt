package com.training.victor.development.di

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.mappers.CommentDataMapper
import com.training.victor.development.data.mappers.PostDataMapper
import com.training.victor.development.data.mappers.UserDataMapper
import com.training.victor.development.data.room.AppDataBase
import com.training.victor.development.di.modules.DataManagerModule
import com.training.victor.development.network.CoyoRepository
import com.training.victor.development.network.ProfilesRepository
import org.mockito.Mockito

class TestDataManagerModule: DataManagerModule() {

    override fun provideDataManager(
        profileRepository: ProfilesRepository,
        coyoRepository: CoyoRepository,
        postDataMapper: PostDataMapper,
        userDataMapper: UserDataMapper,
        commentDataMapper: CommentDataMapper,
        appDataBase: AppDataBase
    ): DataManager {
        return super.provideDataManager(
            profileRepository,
            coyoRepository,
            postDataMapper,
            userDataMapper,
            commentDataMapper,
            appDataBase
        )
    }
}