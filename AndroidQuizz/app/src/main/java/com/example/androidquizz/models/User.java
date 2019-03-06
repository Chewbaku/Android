package com.example.androidquizz.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    // Variables

    @PrimaryKey
    private long id;
    private String login;
    //private String password;

    //Constructeurs
    public User(){}

    public User(long id, String login/*, String password*/){
        this.id = id;
        this.login = login;
        //this.password = password;
    }

    //Getters

    public long getId(){
        return id;
    }

    public String getLogin(){
        return login;
    }
/*
    public String getPassword(){
        return password;
    }
*/
    //Setters
/*
    public void setPassword(String password) {
        this.password = password;
    }
*/
    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
