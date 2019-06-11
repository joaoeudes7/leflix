package com.jedev.leflix.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.jedev.leflix.R;

public class RecuperarSenhaActivity extends AppCompatActivity {

    Button btnRecuperarSenha, btnVoltar;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        btnRecuperarSenha = findViewById(R.id.btnRecuperarSenha);
        btnVoltar = findViewById(R.id.btnVoltar);
        etEmail = findViewById(R.id.etEmail);

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.preenchaEmail, Toast.LENGTH_LONG).show();
                } else {
                    FirebaseAuth auth = FirebaseAuth.getInstance();

                    String emailAddress = etEmail.getText().toString();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), R.string.sucessoRecuperarSenha, Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), R.string.erroRecuperarSenha, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
