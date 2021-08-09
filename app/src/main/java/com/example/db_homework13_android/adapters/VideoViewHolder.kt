package com.example.db_homework13_android.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.db_homework13_android.R

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<TextView>(R.id.videoName)
    val year = itemView.findViewById<TextView>(R.id.videoYear)
    val author = itemView.findViewById<TextView>(R.id.videoAuthor)
    val description = itemView.findViewById<TextView>(R.id.videoDescription)

}