package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.CommentViewModel
import com.training.victor.development.data.models.PostViewModel
import com.training.victor.development.data.models.UserViewModel
import io.reactivex.Scheduler
import javax.inject.Inject

class CoyoPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                       private val subscriberSchedulers: Scheduler,
                                       private val dataManager: DataManager): Presenter<CoyoPresenter.CoyoView>() {

    interface CoyoView: ParentView {
        fun enableProgressBar(enabled: Boolean)
        fun onPostListReceived(postList: List<PostViewModel>)
        fun onPostListError(message: String)
        fun onPostDetailedInfoReceived(userViewModel: UserViewModel, commentList: List<CommentViewModel>)
        fun onPostDetailedInfoError(message: String)
    }


    fun getPostList() {
        view?.enableProgressBar(true)

        disposable.add(dataManager.getPostList()
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.enableProgressBar(false)
                view?.onPostListReceived(it)
            }, {
                view?.enableProgressBar(false)
                view?.onPostListError(it.localizedMessage)
            }))
    }

    fun getPostDetailedInfo(userId: Int, postId: Int) {
        view?.enableProgressBar(true)

        disposable.add(dataManager.getPostDetailedInfo(userId, postId)
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.enableProgressBar(false)
                view?.onPostDetailedInfoReceived(it.first, it.second)
            }, {
                view?.enableProgressBar(false)
                view?.onPostDetailedInfoError(it.localizedMessage)
            }))
    }
}