package com.example.androidquizz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class question extends AppCompatActivity {

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
            int progress = (int) (millisUntilFinished/1000);
            mTimerText.setText(String.valueOf(progress+1));
        }

        @Override
        public void onFinish() {
            mTimerText.setText("FIN");
            finish();
        }
    }
}
