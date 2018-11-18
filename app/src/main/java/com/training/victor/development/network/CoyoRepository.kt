package com.training.victor.development.network

import com.training.victor.development.network.responses.CommentResp
import com.training.victor.development.network.responses.PostResp
import com.training.victor.development.network.responses.UserResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CoyoRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @GET("/posts")
    fun getPost(): Observable<List<PostResp>>

    @GET("/comments")
    fun getComments(@Query("postId") postId: Int): Observable<List<CommentResp>>

    @GET("/users/{userId}")
    fun getUsers(@Path("userId") userId: Int): Observable<UserResp>
}