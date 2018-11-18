package com.training.victor.development.data.mappers

import com.training.victor.development.data.models.PostViewModel
import com.training.victor.development.data.room.PostDto
import com.training.victor.development.network.responses.PostResp

class PostDataMapper {
    fun mapDtoToPostViewModel(postResp: PostResp): PostViewModel {
        return PostViewModel(postResp.userId, postResp.id, postResp.title, postResp.body)
    }

    fun mapDtoToPostViewModel(postDto: PostDto): PostViewModel {
        return PostViewModel(postDto.userId, postDto.id, postDto.title, postDto.body)
    }

    fun mapToPostDto(post: PostResp): PostDto {
        return PostDto(post.userId, post.id, post.title, post.body)
    }

    fun mapListToPostViewModel(postRespList: List<PostResp>): List<PostViewModel> {
        return postRespList.map { mapDtoToPostViewModel(it) }
    }

    fun mapDtoListToPostViewModel(postList: List<PostDto>): List<PostViewModel> {
        return postList.map { mapDtoToPostViewModel(it) }
    }
}