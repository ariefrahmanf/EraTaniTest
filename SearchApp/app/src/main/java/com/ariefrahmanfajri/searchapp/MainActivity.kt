package com.ariefrahmanfajri.searchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariefrahmanfajri.searchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val displayList = mutableListOf<String>()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        searchAdapter = SearchAdapter(dummyData)
        binding.rvResult.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val menuItem = menu.findItem(R.id.search)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(q: String?): Boolean {
                if (q.isNullOrEmpty()) {
                    displayList.clear()
                    searchAdapter = SearchAdapter(dummyData)
                    binding.rvResult.apply {
                        adapter = searchAdapter
                    }
                } else {
                    displayList.clear()
                    dummyData.forEach {
                        if(it.contains(q, false)) {
                            displayList.add(it)
                        }
                    }

                    searchAdapter = SearchAdapter(displayList)

                    binding.rvResult.apply {
                        adapter = searchAdapter
                    }
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}