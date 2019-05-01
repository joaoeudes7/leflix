package com.jedev.leflix.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jedev.leflix.R;
import com.jedev.leflix.service.API.Entities.Volume;
import com.jedev.leflix.service.API.GoogleBookService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testApiBoos();
    }


    private void testApiBoos() {
        new GoogleBookService().searchBook("Kotlin", "40").enqueue(new Callback<Volume>() {
                    @Override
                    public void onResponse(Call<Volume> call, Response<Volume> response) {
                        Volume volume = response.body();
                        Log.d("Ok! Total of Items: ", String.valueOf(volume.totalItems));
                    }

                    @Override
                    public void onFailure(Call<Volume> call, Throwable t) {
                        Log.d("error", String.valueOf(t));
                    }
                });
    }
}
