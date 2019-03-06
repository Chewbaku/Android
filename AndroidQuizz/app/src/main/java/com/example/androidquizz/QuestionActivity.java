package com.example.androidquizz;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidquizz.models.Question;
import com.google.gson.Gson;

public class QuestionActivity extends Activity {

    private Timer timer;
    private TextView timerText;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Recuperation de la question en argument
        String jsonObject = null;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            jsonObject = extras.getString("Question");
        }
        this.question = new Gson().fromJson(jsonObject, Question.class);

        Button answer1, answer2, answer3, answer4;
        TextView questionText;

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        questionText = (TextView) findViewById(R.id.textQuestion);
        timerText = (TextView)findViewById(R.id.timer);
        timer = new Timer(10000, 1000);
        timer.start();

        answer1.setText(this.question.getResponses(1));
        answer2.setText(this.question.getResponses(2));
        answer3.setText(this.question.getResponses(3));
        answer4.setText(this.question.getResponses(4));
        questionText.setText(this.question.getQuestion());

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.player.release();
                timer.cancel();

                if(question.getCorrectAnswer() == 0){
                    question.setFindAnswer(true);
                }
                finish();

            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.player.release();
                timer.cancel();

                if(question.getCorrectAnswer() == 1){
                    question.setFindAnswer(true);
                }
                finish();

            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.player.release();
                timer.cancel();

                if(question.getCorrectAnswer() == 2){
                    question.setFindAnswer(true);
                }
                finish();

            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.player.release();
                timer.cancel();

                if(question.getCorrectAnswer() == 3){
                    question.setFindAnswer(true);
                }
                finish();

            }
        });
    }

    //empêche de quitter la vue via le bouton retour
    @Override
    public void onBackPressed() {
        return;
    }



    public class Timer extends CountDownTimer {

        private MediaPlayer player;

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            player = MediaPlayer.create(QuestionActivity.this, R.raw.coundown);
            player.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished / 1000);
            timerText.setText(String.valueOf(progress + 1));
        }

        @Override
        public void onFinish() {
            timerText.setText("0");
            if(player !=null){
                player.release();
                player = null;
            }
            finish();
        }
    }
}

