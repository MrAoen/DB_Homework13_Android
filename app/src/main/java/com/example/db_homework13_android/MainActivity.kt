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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var rcView: RecyclerView
    private var list: List<Video> = mutableListOf()
    private var lastTemplate = ""

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcView = findViewById(R.id.videoList)
        rcView.layoutManager = LinearLayoutManager(this)
        updateList(lastTemplate)
        rcView.adapter = VideoAdapter(list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val btnNew = findViewById<androidx.appcompat.view.menu.ActionMenuItemView>(R.id.btn_add)
        btnNew.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, AddNewVideo())
                .commit()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateList(template: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            list = withContext(Dispatchers.IO) {
                App.videoDao.selectByAnyContain("%$template%")
            }
            Log.i("DATA_V", list.toString())
        }
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
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    query.apply {
                        lastTemplate = newText ?: ""
                        updateList(lastTemplate)
                        rcView.adapter = VideoAdapter(list)
                        rcView.adapter?.notifyDataSetChanged()
                        rcView.scrollToPosition(0)
                        return true
                    }
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }


}


