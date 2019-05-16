package com.jedev.leflix.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jedev.leflix.R;

public class HomeActivity extends AppCompatActivity {
    private EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        search = findViewById(R.id.seach);

        Intent intent = new Intent();
        intent.putExtra("value", search.getText().toString());

    }
}
