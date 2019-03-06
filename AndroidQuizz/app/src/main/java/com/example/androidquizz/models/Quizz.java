package com.example.androidquizz.models;

import com.example.androidquizz.models.Question;

public class Quizz {
    private Question[] mQuestions;

    public Quizz(Question[] questions) {
        this.mQuestions = questions;
    }

    public Question[] getQuestions() {
        return mQuestions;
    }
}
