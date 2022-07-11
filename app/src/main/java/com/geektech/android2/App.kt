package com.geektech.android2

import android.app.Application
import androidx.room.Room
import com.geektech.android2.room.AppDataBase

class App : Application() {

    var instance:App?=null

    companion object{
        lateinit var dataBase: AppDataBase
    }


    override fun onCreate() {
        super.onCreate()
        dataBase=Room.databaseBuilder(this,AppDataBase::class.java, "database").allowMainThreadQueries().build()
    }
}