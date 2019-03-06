package com.example.androidquizz.models;

public class Quizz {
    private Question[] mQuestions;

    public Quizz(Question[] questions) {
        this.mQuestions = questions;
    }

    public Question[] getQuestions() {
        return mQuestions;
    }
}