package com.jedev.leflix.Activies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.jedev.leflix.Model.Usuario;
import com.jedev.leflix.R;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private TextView abrirlogin;

    private Usuario usuario;


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

                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                //Validar se os campos foram preenchidos
                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {

                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            usuario.setActive(true);
                            usuario.setCreated_on(DateCustom.dataAtual());
                            usuario.setOfencive_days(0);
                            usuario.setLast_date_ofensive("0");
                            cadastrarUsuario();

                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this,
                                "Preencha o email!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();
                    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                    finish();

                } else {

                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais forte!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Por favor, digite um e-mail vÃ¡lido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Este conta jÃ¡ foi cadastrada";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuÃ¡rio: " + e.getMessage();
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
        startActivity(new Intent(this,LoginActivity.class));
        finish();



    }

}
