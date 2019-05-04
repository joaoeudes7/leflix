package com.jedev.leflix.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.jedev.leflix.Config.ConfiguracaoFirebase;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private String created_on;
    private int ofencive_days;
    private String last_date_ofensive;

    public User() {

    }

    public User(String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, Boolean active, String created_on, Integer ofencive_days, String last_date_ofensive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.created_on = created_on;
        this.ofencive_days = ofencive_days;
        this.last_date_ofensive = last_date_ofensive;
    }

    public User(String id, String name, String email, String password, Boolean active, String created_on, Integer ofencive_days, String last_date_ofensive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.created_on = created_on;
        this.ofencive_days = ofencive_days;
        this.last_date_ofensive = last_date_ofensive;
    }

    public void salvar(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("usuarios")
                .child(this.id)
                .setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
