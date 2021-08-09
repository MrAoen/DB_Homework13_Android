package com.example.db_homework13_android.model.repository

import androidx.room.*
import com.example.db_homework13_android.model.entity.Video

@Dao
interface VideoDao {

    @Insert
    suspend fun insert(vararg video:Video)

    @Delete
    suspend fun delete(vararg video:Video)

    @Update
    suspend fun update(vararg video:Video)

    @Query("select * from video")
    suspend fun selectAll():List<Video>

    @Query("select * from video v where v.author like :template or v.description like :template or v.name like:template")
    suspend fun selectByAnyContain(template:String):List<Video>
}