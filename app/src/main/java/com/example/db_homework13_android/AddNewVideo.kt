package com.example.db_homework13_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.db_homework13_android.model.entity.Video
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewVideo : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new_video, container, false)

        val btn_save = view.findViewById<Button>(R.id.saveNewVideo)
        btn_save.setOnClickListener {
            val vName = view.findViewById<TextInputEditText>(R.id.newName)
            val vAuthor = view.findViewById<TextInputEditText>(R.id.newAuthor)
            val vYear = view.findViewById<TextInputEditText>(R.id.newYear)
            val vDescription = view.findViewById<TextInputEditText>(R.id.newDescription)
            val video = Video(
                vName.text.toString(),
                vYear.text.toString().toInt(),
                vAuthor.text.toString(),
                vDescription.text.toString()
            )
            lifecycleScope.launch(Dispatchers.IO) {
                App.videoDao.insert(video)
            }
            parentFragmentManager.popBackStack()
        }

        return view
    }


}