package com.example.db_homework13_android

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.db_homework13_android.model.DatabaseConfig
import com.example.db_homework13_android.model.repository.VideoDao
import java.util.concurrent.Executors

class App : Application() {

    companion object {
        lateinit var videoDao: VideoDao
    }

    override fun onCreate() {
        super.onCreate()
        val db = Room.databaseBuilder(applicationContext, DatabaseConfig::class.java, "video-db")
        db.setQueryCallback(RoomDatabase.QueryCallback { sqlQuery, bindArgs ->
            println("SQL Query: $sqlQuery SQL Args: $bindArgs")
        }, Executors.newSingleThreadExecutor())
        videoDao = db.build().videoDao()
    }
}