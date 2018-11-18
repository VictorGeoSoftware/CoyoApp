package com.training.victor.development.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Maybe

@Dao
interface PostDao {

    @Query("SELECT * FROM POST_DB")
    fun getAllPost(): Maybe<List<PostDto>>

    @Query("DELETE FROM POST_DB")
    fun clearAllPosts()
}