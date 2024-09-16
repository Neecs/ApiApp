package com.neecs.apiapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharactersAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CharactersAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.uiState.observe(this, Observer { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // Show loading indicator
                }
                is UiState.Success -> {
                    adapter.updateData(uiState.data)
                }
                is UiState.Error -> {
                    Toast.makeText(this, uiState.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.fetchCharacters()
    }
}