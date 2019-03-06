package com.example.androidquizz.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId"))
public class Statistics {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private int nbPlayedAnswers;
    private int nbGoodAnswers;
    private long userId;

    public Statistics(){}

    public Statistics(int nbPlayedAnswers, int nbGoodAnswers, long userId){
        this.nbPlayedAnswers = nbPlayedAnswers;
        this.nbGoodAnswers = nbGoodAnswers;
        this.userId = userId;
    }

    //Getters

    public long getId() {
        return id;
    }

    public int getNbPlayedAnswers() {
        return nbPlayedAnswers;
    }

    public int getNbGoodAnswers() {
        return nbGoodAnswers;
    }

    public long getUserId() {
        return userId;
    }

    //Setters

    public void setId(long id) {
        this.id = id;
    }

    public void setNbPlayedAnswers(int nbPlayedAnswers) {
        this.nbPlayedAnswers = nbPlayedAnswers;
    }

    public void setNbGoodAnswers(int nbGoodAnswers) {
        this.nbGoodAnswers = nbGoodAnswers;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
