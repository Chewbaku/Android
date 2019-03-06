package com.example.androidquizz.models;

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

    private int correctAnswer;
    private int difficulty;
    private boolean mFindAnswer = false;

    public Question(String question, String[] responses, String answer, String theme, String difficulty) {
        mQuestion = question;
        mResponses = responses;
        mAnswer = answer;
        mTheme = theme;
        mDifficulty = difficulty;
    }

    public void update() {
        this.correctAnswer = Integer.parseInt(this.getAnswer());
        this.difficulty = Integer.parseInt(this.getStringDifficulty());
    }

    public String getQuestion() {
        return this.mQuestion;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

    public int getIntDiffuculty() {
        return this.difficulty;
    }

    public String getAnswer() {
        return this.mAnswer;
    }

    public String getTheme() {
        return this.mTheme;
    }

    public String getStringDifficulty() {
        return this.mDifficulty;
    }

    public String getResponses(int i) {
        return this.mResponses[i-1];
    }

    public boolean getFindAnswer() {
        return this.mFindAnswer;
    }

    public void setFindAnswer(boolean answer) {
        this.mFindAnswer = answer;
    }

    @Override
    public String toString() {
        return "\nQuestion{\n" +
                "mQuestion=" + mQuestion + '\n' +
                "mResponses=" + Arrays.toString(mResponses) + '\n' +
                "mAnswer=" + mAnswer + '\n' +
                "mTheme=" + mTheme + '\n' +
                "mDifficulty=" + mDifficulty + '\n' +
                "correctAnswer=" + correctAnswer + '\n' +
                "difficulty=" + difficulty + '\n' +
                "}\n";
    }
}
