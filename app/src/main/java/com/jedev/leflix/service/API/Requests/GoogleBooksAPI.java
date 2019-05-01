package com.jedev.leflix.service.API.Requests;

import com.jedev.leflix.service.API.Entities.Volume;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksAPI {

    @GET("volumes?printType=books")
    Call<Volume> searchVolumes(
            @Query("key") String apiKey,
            @Query("langRestrict") String lang,
            @Query("maxResults") String maxResults,
            @Query("q") String termSearch
    );
}
