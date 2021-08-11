package com.example.db_homework13_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.db_homework13_android.R
import com.example.db_homework13_android.model.entity.Video

class VideoAdapter(private val videoList: List<Video>) :
    RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        videoList[position].apply {
            holder.author?.text = author
            holder.description?.text = description
            holder.name?.text = name
            holder.year?.text = year.toString()
        }
    }

    override fun getItemCount() = videoList.size


}