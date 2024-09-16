package com.neecs.apiapp

import retrofit2.Call
import retrofit2.http.GET

interface DisneyApiService {
    @GET("character")
    fun getCharacters(): Call<DisneyApiResponse>
}