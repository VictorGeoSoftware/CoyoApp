package com.training.victor.development.data.mappers

import com.training.victor.development.data.models.CommentViewModel
import com.training.victor.development.network.responses.CommentResp

class CommentDataMapper {
    private fun mapToCommentViewModel(commentResp: CommentResp): CommentViewModel {
        return CommentViewModel(commentResp.id, commentResp.name, commentResp.email, commentResp.body)
    }

    fun mapListToCommentViewModelList(commentRespList: List<CommentResp>): List<CommentViewModel> {
        return commentRespList.map { mapToCommentViewModel(it) }
    }
}