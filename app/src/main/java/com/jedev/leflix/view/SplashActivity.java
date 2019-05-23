package com.jedev.leflix.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jedev.leflix.R;

public class SplashActivity extends AppCompatActivity {
    //Mudei o tempo, pra ser mais rápido na hora de reiniciar o app
    private static final int TEMPO_SPLASH = 1000;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();



        View decorView = getWindow().getDecorView();
        // Esconde tanto a barra de navegação e a barra de status .
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        getSupportActionBar().hide(); // esconde actionbar

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (auth != null) {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();


                } else {
                    Intent e = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(e);
                    finish();
                }
            }
        }, TEMPO_SPLASH);
    }

}

