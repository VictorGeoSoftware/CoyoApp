package com.training.victor.development.network

import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.network.responses.ProfileDetailResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProfilesRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @GET("/profiles")
    fun getProfilesList(): Observable<ArrayList<ProfileItem>>

    @GET("/profiles")
    fun getProfileDetail(@Query("id") id: Int): Observable<ProfileDetailResp>
}