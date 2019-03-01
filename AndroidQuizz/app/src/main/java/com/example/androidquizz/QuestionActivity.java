package com.example.androidquizz;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class QuestionActivity extends Activity {

    Timer mTimer;
    TextView mTimerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mTimerText = (TextView)findViewById(R.id.timer);
        mTimer = new Timer(10000, 1000);
        mTimer.start();
    }

    public class Timer extends CountDownTimer {

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished / 1000);
            mTimerText.setText(String.valueOf(progress + 1));
        }

        @Override
        public void onFinish() {
            mTimerText.setText("0");
            finish();
        }
    }
}

