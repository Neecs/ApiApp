package com.neecs.apiapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchCharacters()
    }

    private fun fetchCharacters() {
        RetrofitClient.instance.getCharacters().enqueue(object : Callback<DisneyApiResponse> {
            override fun onResponse(call: Call<DisneyApiResponse>, response: Response<DisneyApiResponse>) {
                if (response.isSuccessful) {
                    val characters = response.body()?.data ?: emptyList()
                    adapter = CharactersAdapter(characters)
                    recyclerView.adapter = adapter
                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DisneyApiResponse>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}