package com.training.victor.development.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.training.victor.development.data.Constants.Companion.POST_DB

@Entity(tableName = POST_DB)
class PostDto(@PrimaryKey val id: Int,
              val userId: Int,
              val title: String,
              val body: String)
