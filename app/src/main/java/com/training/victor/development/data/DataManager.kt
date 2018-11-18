package com.training.victor.development.data

import com.training.victor.development.data.mappers.CommentDataMapper
import com.training.victor.development.data.mappers.PostDataMapper
import com.training.victor.development.data.mappers.UserDataMapper
import com.training.victor.development.data.models.CommentViewModel
import com.training.victor.development.data.models.PostViewModel
import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.data.models.UserViewModel
import com.training.victor.development.data.room.AppDataBase
import com.training.victor.development.network.CoyoRepository
import com.training.victor.development.network.ProfilesRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.*

class DataManager(private val profilesRepository: ProfilesRepository,
                  private val coyoRepository: CoyoRepository,
                  private val postDataMapper: PostDataMapper,
                  private val userDataMapper: UserDataMapper,
                  private val commentDataMapper: CommentDataMapper,
                  val appDataBase: AppDataBase) {

    var dateLastRequest = Date()

    fun getProfilesList(): Observable<ArrayList<ProfileItem>> {
        return profilesRepository.getProfilesList().flatMap {
            Observable.just(it)
        }
    }

    fun getPostList(): Observable<List<PostViewModel>> {
        val rightNow = Date()
        val dateToCompare = Date(dateLastRequest.time + 5*60*1000)

        return if (rightNow.after(dateToCompare)) {
            dateLastRequest = dateToCompare
            getPostListFromApi()
        }  else {
            appDataBase.postDao().getAllPost().toObservable().flatMap { it ->
                if (it.isEmpty()) {
                    getPostListFromApi()
                } else {
                    Observable.just(it.map { postDataMapper.mapDtoToPostViewModel(it) }) }
                }

        }
    }

    fun getPostListFromApi(): Observable<List<PostViewModel>> {
        return coyoRepository.getPost().flatMap { postRespList ->

            appDataBase.postDao().clearAllPosts()
            postRespList.map { appDataBase.postDao().addPost(postDataMapper.mapToPostDto(it)) }
            appDataBase.postDao().getAllPost().flatMapObservable {it ->
                Observable.just(postDataMapper.mapDtoListToPostViewModel(it)) }
        }
    }

    fun getPostUser(userId: Int): Observable<UserViewModel> {
        return coyoRepository.getUsers(userId).flatMap {
            Observable.just(userDataMapper.mapToUserViewModel(it))
        }
    }

    fun getPostComments(postId: Int): Observable<List<CommentViewModel>> {
        return coyoRepository.getComments(postId).flatMap {
            Observable.just(commentDataMapper.mapListToCommentViewModelList(it))
        }
    }

    fun getPostDetailedInfo(userId: Int, postId: Int): Observable<Pair<UserViewModel, List<CommentViewModel>>> {
        return Observable.zip(getPostUser(userId),
            getPostComments(postId), BiFunction{ user, commentList ->
                Pair(user, commentList)
            })
    }
}