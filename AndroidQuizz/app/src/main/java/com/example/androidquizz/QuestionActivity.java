package com.example.androidquizz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends Activity {

    private Timer timer;
    private TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        timerText = (TextView)findViewById(R.id.timer);
        timer = new Timer(10000, 1000);
        timer.start();

        Button answer1;
        answer1 = (Button) findViewById(R.id.answer1);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.player.release();
                timer.cancel();
                Intent intent = new Intent(QuestionActivity.this, QuestionActivity.class);
                startActivity(intent);
                finish();

            }
        });
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

