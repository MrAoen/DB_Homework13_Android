package com.example.db_homework13_android.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.db_homework13_android.model.entity.Video
import com.example.db_homework13_android.model.repository.VideoDao

@Database(entities = [Video::class], version = 1)
abstract class DatabaseConfig : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}