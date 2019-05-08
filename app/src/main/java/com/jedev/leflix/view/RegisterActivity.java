package com.jedev.leflix.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.jedev.leflix.Config.ConfiguracaoFirebase;
import com.jedev.leflix.Helper.Base64Custom;
import com.jedev.leflix.Helper.DateCustom;
import com.jedev.leflix.Model.User;
import com.jedev.leflix.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private TextView abrirlogin;

    private User usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        campoNome = findViewById(R.id.regnome);
        campoEmail = findViewById(R.id.regemail);
        campoSenha = findViewById(R.id.regsenha);
        abrirlogin=findViewById(R.id.telalogin);
        botaoCadastrar = findViewById(R.id.regcadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = campoNome.getText().toString();
                String email = campoEmail.getText().toString();
                String password = campoSenha.getText().toString();
                String createdOn = DateCustom.dataAtual();

                //Validar se os campos foram preenchidos
                if (!name.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!password.isEmpty()) {

                            usuario = new User(name, email, password, true, createdOn, 0, "");
                            cadastrarUsuario();

                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    getString(R.string.preenchaSenha),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this,
                                getString(R.string.preenchaEmail),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,
                            getString(R.string.preenchaNome),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setId(idUsuario);
                    usuario.salvar();
                    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                    finish();

                } else {

                    String excecao = "";
                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = getString(R.string.senhaFraca);
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = getString(R.string.emailInvalido);
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = getString(R.string.emailDuplicado);
                    } catch (Exception e) {
                        excecao = getString(R.string.erroCadastrousuario) + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(RegisterActivity.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void abrirlogin(View view){
        startActivity(new Intent(this, com.jedev.leflix.view.LoginActivity.class));
        //  finish();



    }

}
