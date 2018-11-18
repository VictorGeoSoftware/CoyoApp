package com.training.victor.development.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [PostDto::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun postDao(): PostDao
}