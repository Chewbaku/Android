package com.example.androidquizz;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.text.TextUtils;

import com.example.androidquizz.database.DatabaseHelper;
import com.example.androidquizz.models.Statistics;
import com.example.androidquizz.models.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText loginEditText;
    private User user = new User();

    private DatabaseHelper dbh = DatabaseHelper.getInstance();
    final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEditText = findViewById(R.id.loginEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( (TextUtils.isEmpty(loginEditText.getText())) || (loginEditText.getText().length() > 20) ) {
                    return;
                }

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        User user = new User();
                        Statistics statistics = new Statistics();
                        user.setLogin(loginEditText.getText().toString());
                        user.setId(0);
                        dbh.getUserDao().createUser(user);
                        statistics.setId(0);
                        statistics.setNbGoodAnswers(0);
                        statistics.setNbPlayedAnswers(0);
                        statistics.setUserId(user.getId());
                        dbh.getStatisticsDao().insertStatistics(statistics);

                    }
                });

                Intent intent = new Intent(MainActivity.this, LobbyActivity.class);
                startActivity(intent);

            }
        });
    }


}
