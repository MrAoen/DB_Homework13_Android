package com.example.db_homework13_android.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.db_homework13_android.R

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var description: TextView?
    var author: TextView?
    val name :TextView?
    val year :TextView?

    init {
        name = itemView.findViewById(R.id.videoName)
        year = itemView.findViewById(R.id.videoYear)
        author = itemView.findViewById(R.id.videoAuthor)
        description = itemView.findViewById(R.id.videoDescription)
    }
}