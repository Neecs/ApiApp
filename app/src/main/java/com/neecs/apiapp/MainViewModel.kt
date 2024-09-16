package com.neecs.apiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    // LiveData to hold the list of characters
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    // Function to fetch characters from the API
    fun fetchCharacters() {
        RetrofitClient.instance.getCharacters().enqueue(object : Callback<DisneyApiResponse> {
            override fun onResponse(call: Call<DisneyApiResponse>, response: Response<DisneyApiResponse>) {
                if (response.isSuccessful) {
                    _characters.value = response.body()?.data ?: emptyList()
                }
            }

            override fun onFailure(call: Call<DisneyApiResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}