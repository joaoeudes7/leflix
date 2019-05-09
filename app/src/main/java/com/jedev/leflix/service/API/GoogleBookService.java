package com.jedev.leflix.service.API;

import com.jedev.leflix.service.API.Entities.Volume;
import com.jedev.leflix.service.API.Requests.GoogleBooksAPI;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleBookService {

    private static Retrofit retrofit;
    private static GoogleBooksAPI googleBooksAPI;

    private static final String APP_KEY = "AIzaSyAUmtFwNet9BRJzlBUe22u4dFGyj_sHL94";
    private static final String DEFAULT_LANGUAGE = "pt";

    public GoogleBookService() {
        if (retrofit == null) {
            String BASE_URL = "https://www.googleapis.com/books/v1/";

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (googleBooksAPI == null) {
            googleBooksAPI = retrofit.create(GoogleBooksAPI.class);
        }
    }

    public Call<Volume> searchBook(String term, String maxResults) {
        return googleBooksAPI.searchVolumes(APP_KEY, DEFAULT_LANGUAGE, maxResults, term);
    }

    public Call<Volume> getBookbyID(String id) {
        return googleBooksAPI.getById(id);
    }

}
