package com.jedev.leflix.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.jedev.leflix.Config.ConfiguracaoFirebase;

import java.util.Date;

public class Usuario {
    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private Boolean active;
    private String created_on;
    private int ofencive_days;
    private String last_date_ofensive;

    public Usuario() {
    }
    public void salvar(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("usuarios")
                .child( this.idUsuario )
                .setValue( this );
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getLast_date_ofensive() {
        return last_date_ofensive;
    }

    public void setLast_date_ofensive(String last_date_ofensive) {
        this.last_date_ofensive = last_date_ofensive;
    }

    public int getOfencive_days() {
        return ofencive_days;
    }

    public void setOfencive_days(int ofencive_days) {
        this.ofencive_days = ofencive_days;
    }




}
