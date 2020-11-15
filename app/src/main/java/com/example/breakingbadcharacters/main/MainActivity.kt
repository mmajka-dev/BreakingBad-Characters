package com.example.breakingbadcharacters.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.databinding.ActivityMainBinding
import com.example.breakingbadcharacters.models.CharacterItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), onClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        GlobalScope.launch {
            getCharacters(binding.root)
        }

        viewModel.characters.observe(this, Observer { newCharacters ->
            Log.i("Characters: ", "${newCharacters[0]}")
            viewModel.characters.observe(this, Observer {
                val layoutManager = GridLayoutManager(binding.root.context, 2)
                adapter = CharacterAdapter(it, this)
                binding.recyclerView.layoutManager = layoutManager
                binding.recyclerView.adapter = adapter
            })
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.app_bar_search, menu)
//        val searchViewItem: MenuItem = menu!!.findItem(R.id.search)
//        val searchView: SearchView = searchViewItem.actionView as SearchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
//                return false
//            }
//        })
//
//        return super.onCreateOptionsMenu(menu)
//    }

    private fun getCharacters(view: View){
        viewModel.getCharacters(view)
    }

    override fun onClick(position: Int, view: View, character: CharacterItem) {
        Snackbar.make(binding.root, "Click", Snackbar.LENGTH_SHORT).show()
    }
}