package com.training.victor.development.data.mappers

import com.training.victor.development.data.models.UserViewModel
import com.training.victor.development.network.responses.UserResp

class UserDataMapper {
    fun mapToUserViewModel(userResp: UserResp): UserViewModel {
        return UserViewModel(userResp.name, userResp.userName, userResp.email)
    }
}