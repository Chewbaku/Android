package com.example.androidquizz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class QuizzActivity extends Activity implements  View.OnClickListener{

    private Timer timer;
    private TextView timerText;
    private Question[] mQuestions;
    private  int mQuestionNumber;

    private Button mAnswer1, mAnswer2, mAnswer3, mAnswer4;
    private TextView mQuestionText, mQuestionID;

    private boolean mQuestionAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        this.fetchPost();
    }

    private void fetchPost() {
        StringRequest request = new StringRequest(Request.Method.GET,ENDPOINT, onQuestionsLoaded, onQuestionsError);
        this.mRequestQueue.add(request);
    }
    
    private final Response.Listener<String> onQuestionsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

        if (answerIndex == this.mQuestions[this.mQuestionNumber].getCorrectAnswer()) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "U stuped", Toast.LENGTH_SHORT).show();
        }

        if ((this.mQuestionNumber + 1) == 5) {
            this.endQuizz();
        } else {
            this.mQuestionNumber++;
            this.afficherQuestion(this.mQuestions[this.mQuestionNumber]);
        }
    }

    private void afficherQuestion(Question question) {
        this.mAnswer1.setText(question.getResponses(1));
        this.mAnswer2.setText(question.getResponses(2));
        this.mAnswer3.setText(question.getResponses(3));
        this.mAnswer4.setText(question.getResponses(4));
        this.mQuestionText.setText(question.getQuestion());
        this.mQuestionID.setText(String.valueOf(1+this.mQuestionNumber));
        if(this.timer != null) {
            if(this.timer.player!=null){
                this.timer.player.release();
            }
            this.timer.cancel();
        }
        this.timer = new Timer(10000, 1000);
        this.timer.start();
    }

    private void endQuizz() {
        if(this.timer != null) {
            if(this.timer.player!=null){
                this.timer.player.release();
            }
            this.timer.cancel();
        }
        Intent intent = new Intent(QuizzActivity.this, ScoreActivity.class);
        startActivity(intent);
    }

    
    //empêche de quitter la vue via le bouton retour
    @Override
    public void onBackPressed() {
        return;
    }



    public class Timer extends CountDownTimer {

        private MediaPlayer player;
        private boolean mIsRunning = false;

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            player = MediaPlayer.create(QuizzActivity.this, R.raw.coundown);
            player.start();
        }

    };

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished / 1000);
            timerText.setText(String.valueOf(progress + 1));
            this.mIsRunning = true;
        }

        @Override
        public void onFinish() {
            timerText.setText("0");
            this.mIsRunning = false;
            if (player != null) {
                player.release();
                player = null;
            }
            if ((mQuestionNumber + 1) == 5) {
                endQuizz();
            } else {
                mQuestionNumber++;
                afficherQuestion(mQuestions[mQuestionNumber]);
            }
        }

        public boolean isRunning() {
            return this.mIsRunning;
        }
    }
}


