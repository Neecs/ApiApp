package com.neecs.apiapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.neecs.apiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter with an empty list
        adapter = CharactersAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.uiState.observe(this, Observer { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // Show loading state
                }
                is UiState.Success -> {
                    // Update RecyclerView with data
                    adapter.updateData(uiState.data)
                }
                is UiState.Error -> {
                    // Show error message
                }
            }
        })

        viewModel.fetchCharacters()
    }
}