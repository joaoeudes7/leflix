package com.jedev.leflix.service.api.requests

import com.jedev.leflix.service.api.entities.Volume

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksAPI {

    @GET("volumes?printType=books&orderBy=newest")
    fun searchVolumes(
            @Query("key") apiKey: String,
            @Query("langRestrict") lang: String,
            @Query("maxResults") maxResults: Int,
            @Query("q") termSearch: String
    ): Call<Volume>

    @GET("volumes/{volumeId}")
    fun getById(
            @Path("volumeId") volumeId: String,
            @Query("key") apiKey: String
    ): Call<Volume>
}
