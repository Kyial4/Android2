package com.geektech.android2.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.android2.models.News

@Database(entities = [News::class], version=1)

abstract class AppDataBase: RoomDatabase() {

    abstract fun newsDao():NewsDao
}