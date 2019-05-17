package com.jedev.leflix.service.api

import com.jedev.leflix.service.api.entities.Volume
import com.jedev.leflix.service.api.requests.GoogleBooksAPI

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GoogleBookService {
    companion object {
        private val APP_KEY = "AIzaSyAUmtFwNet9BRJzlBUe22u4dFGyj_sHL94"
        private val DEFAULT_LANGUAGE = "pt"

        private var retrofit: Retrofit? = null
        private var googleBooksAPI: GoogleBooksAPI? = null
    }

    init {
        if (retrofit == null) {
            val BASE_URL = "https://www.googleapis.com/books/v1/"

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        if (googleBooksAPI == null) {
            googleBooksAPI = retrofit!!.create(GoogleBooksAPI::class.java)
        }
    }

    fun searchBook(term: String, maxResults: String): Call<Volume> {
        return googleBooksAPI!!.searchVolumes(APP_KEY, DEFAULT_LANGUAGE, maxResults, term)
    }

    fun getBookbyID(id: String): Call<Volume> {
        return googleBooksAPI!!.getById(id)
    }
}
