package com.neecs.apiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UiState<List<Character>>>()
    val uiState: LiveData<UiState<List<Character>>> get() = _uiState

    fun fetchCharacters() {
        _uiState.value = UiState.Loading
        RetrofitClient.instance.getCharacters().enqueue(object : Callback<DisneyApiResponse> {
            override fun onResponse(call: Call<DisneyApiResponse>, response: Response<DisneyApiResponse>) {
                if (response.isSuccessful) {
                    _uiState.value = UiState.Success(response.body()?.data ?: emptyList())
                } else {
                    _uiState.value = UiState.Error("Error fetching data")
                }
            }

            override fun onFailure(call: Call<DisneyApiResponse>, t: Throwable) {
                _uiState.value = UiState.Error(t.message ?: "Unknown error")
            }
        })
    }
}