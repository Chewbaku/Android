package com.example.androidquizz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidquizz.models.Question;
import com.google.gson.Gson;


public class QuizzActivity extends Activity implements  View.OnClickListener{

    private Timer timer;
    private TextView timerText;
    private Question[] mQuestions;
    private int mQuestionNumber;
    private int score = 0;

    private Button mAnswer1, mAnswer2, mAnswer3, mAnswer4;
    private TextView mQuestionText, mQuestionID;

    private boolean mQuestionAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);

        this.mQuestions = new Question[5];
        this.mQuestionNumber = 0;

        // Recuperation de la question en argument
        String jsonObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            for (int i = 0; i < 5; i++) {
                jsonObject = extras.getString("Question" + i);
                this.mQuestions[i] = new Gson().fromJson(jsonObject, Question.class);
            }
        }

        this.mAnswer1 = findViewById(R.id.answer1);
        this.mAnswer2 = findViewById(R.id.answer2);
        this.mAnswer3 = findViewById(R.id.answer3);
        this.mAnswer4 = findViewById(R.id.answer4);

        mAnswer1.setTag(0);
        mAnswer2.setTag(1);
        mAnswer3.setTag(2);
        mAnswer4.setTag(3);

        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);

        this.mQuestionText = (TextView) findViewById(R.id.textQuestion);
        this.mQuestionID = (TextView) findViewById(R.id.intNumeroQuestion);

        timerText = (TextView) findViewById(R.id.timer);

        this.afficherQuestion(this.mQuestions[this.mQuestionNumber]);
    }

    @Override
    public void onClick(View v) {
        int answerIndex = (int) v.getTag();

        if (answerIndex == this.mQuestions[this.mQuestionNumber].getCorrectAnswer()) {
            this.score++;
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
        intent.putExtra("Score", this.score);
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
