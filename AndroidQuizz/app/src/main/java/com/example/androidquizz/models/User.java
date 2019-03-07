package com.example.androidquizz.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    // Variables

    @PrimaryKey @NonNull
    private long id;
    @ColumnInfo @NonNull
    private String login;
    //private String password;
    @ColumnInfo @NonNull
    private int nbPlayedAnswers;
    @ColumnInfo @NonNull
    private int nbGoodAnswers;

    //Constructeurs
    public User(){}

    public User(long id, String login/*, String password*/){
        this.id = id;
        this.login = login;
        //this.password = password;
    }

    //Getters

    public int getNbPlayedAnswers() {
        return nbPlayedAnswers;
    }

    public void setNbPlayedAnswers(int nbPlayedAnswers) {
        this.nbPlayedAnswers = nbPlayedAnswers;
    }

    public int getNbGoodAnswers() {
        return nbGoodAnswers;
    }

    public void setNbGoodAnswers(int nbGoodAnswers) {
        this.nbGoodAnswers = nbGoodAnswers;
    }

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
