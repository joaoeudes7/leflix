package com.jedev.leflix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.jedev.leflix.Model.Usuario;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;

    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        campoNome = findViewById(R.id.regsenha);
        campoEmail = findViewById(R.id.regemail);
        campoSenha = findViewById(R.id.regsenha);
        botaoCadastrar = findViewById(R.id.regcadastrar);



    }
}
