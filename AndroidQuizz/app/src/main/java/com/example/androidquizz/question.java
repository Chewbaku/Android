package com.example.androidquizz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class question extends AppCompatActivity {

    MyTimer myCountDownTimer;
    TextView mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mCount = (TextView)findViewById(R.id.count);
        myCountDownTimer = new MyTimer(10000, 1000);
        myCountDownTimer.start();
    }

    public class MyTimer extends CountDownTimer {

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished/1000);

            mCount.setText(String.valueOf(progress+1));
        }

        @Override
        public void onFinish() {
            mCount.setText("FIN");
            finish();
        }
    }
}
