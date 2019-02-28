package com.example.androidquizz;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Question {
    @SerializedName("question")
    private final String mQuestion;

    @SerializedName("reponses")
    private final String[] mResponses;

    @SerializedName("reponse_correcte")
    private final String mAnswer;

    @SerializedName("theme")
    private final String mTheme;

    @SerializedName("difficulte")
    private final String mDifficulty;

    public Question(String question, String[] responses, String answer, String theme, String difficulty) {
        mQuestion = question;
        mResponses = responses;
        mAnswer = answer;
        mTheme = theme;
        mDifficulty = difficulty;
    }

    public String getQuestion() {
        return mQuestion;
    }


    public String getAnswer() {
        return mAnswer;
    }

    public String getTheme() {
        return mTheme;
    }

    public String getDifficulty() {
        return mDifficulty;
    }

    public String[] getResponses() {
        return mResponses;
    }

    @Override
    public String toString() {
        return "Question{\n" +
                "mQuestion= " + mQuestion + '\n' +
                "mResponses= " + Arrays.toString(mResponses) + '\n' +
                "mAnswer= " + mAnswer + '\n' +
                "mTheme= " + mTheme + '\n' +
                "mDifficulty= " + mDifficulty + '\n' +
                '}';
    }
}
