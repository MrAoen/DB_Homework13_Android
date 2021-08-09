package com.example.db_homework13_android.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Video(
    @ColumnInfo
    val name:String?,
    @ColumnInfo
    val year:Int?,
    @ColumnInfo
    val author:String?,
    @ColumnInfo
    val description:String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}