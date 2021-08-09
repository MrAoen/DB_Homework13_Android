package com.example.db_homework13_android

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.db_homework13_android.adapters.VideoAdapter
import com.example.db_homework13_android.model.entity.Video
import com.example.db_homework13_android.model.repository.VideoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var videoDao: VideoDao
    private lateinit var rcView: RecyclerView
    private var list: List<Video> = listOf<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //videoDao = App.videoDao
        rcView = findViewById(R.id.videoList)
        updateList()
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = VideoAdapter(list, this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val btnNew = findViewById<androidx.appcompat.view.menu.ActionMenuItemView>(R.id.btn_add)
        btnNew.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, AddNewVideo())
                .commit()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateList() {
        lifecycleScope.launch(Dispatchers.IO) {
            list = withContext(Dispatchers.IO) {
                App.videoDao.selectAll()
            }
            Log.i("DATA_V", list.toString())
        }
        rcView.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateList(template: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            list = withContext(Dispatchers.IO) {
                App.videoDao.selectByAnyContain("%$template%")
            }
            Log.i("DATA_V", list.toString())
        }
        rcView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.menu_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query.apply {
                        updateList(query ?: "")
                        return true
                    }
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    query.apply {
                        updateList(newText ?: "")
                        return true
                    }
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }


}


