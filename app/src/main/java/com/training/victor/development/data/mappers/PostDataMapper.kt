package com.training.victor.development.data.mappers

import com.training.victor.development.data.models.PostViewModel
import com.training.victor.development.network.responses.PostResp

class PostDataMapper {
    fun mapToPostViewModel(postResp: PostResp): PostViewModel {
        return PostViewModel(postResp.userId, postResp.id, postResp.title, postResp.body)
    }

    fun mapListToPostViewModel(postRespList: List<PostResp>): List<PostViewModel> {
        return postRespList.map { mapToPostViewModel(it) }
    }
}