package com.example.androidquizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidquizz.database.DatabaseHelper;
import com.example.androidquizz.models.Statistics;
import com.example.androidquizz.models.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    final Executor executor = Executors.newSingleThreadExecutor();
    private DatabaseHelper dbh = DatabaseHelper.getInstance();


    private int mScore;
    private String mReponse1;
    private String mReponse2;
    private String mReponse3;
    private String mReponse4;
    private String mReponse5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        this.mScore = getIntent().getIntExtra("Score", -1);

        mReponse1 = getIntent().getStringExtra("Reponse1");
        mReponse2 = getIntent().getStringExtra("Reponse2");
        mReponse3 = getIntent().getStringExtra("Reponse3");
        mReponse4 = getIntent().getStringExtra("Reponse4");
        mReponse5 = getIntent().getStringExtra("Reponse5");

        executor.execute(new Runnable() {
            @Override
            public void run() {
                User user = dbh.getUserDao().getUser(0);
                Statistics statistics = dbh.getStatisticsDao().getStatistics(user.getId());

                TextView txtScore = findViewById(R.id.intScore);
                TextView txtScoreQuestion1 = findViewById(R.id.intReponse1);
                TextView txtScoreQuestion2 = findViewById(R.id.intReponse2);
                TextView txtScoreQuestion3 = findViewById(R.id.intReponse3);
                TextView txtScoreQuestion4 = findViewById(R.id.intReponse4);
                TextView txtScoreQuestion5 = findViewById(R.id.intReponse5);

                txtScore.setText(String.valueOf(mScore));
                txtScoreQuestion1.setText("0");
                txtScoreQuestion2.setText("0");
                txtScoreQuestion3.setText("0");
                txtScoreQuestion4.setText("0");
                txtScoreQuestion5.setText("0");
            }
        });

        Button back;
        Button share;

        share = findViewById(R.id.partager);
        back = (Button) findViewById(R.id.button);

        //partage
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt(mScore);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, LobbyActivity.class);
                startActivity(intent);

            }
        });
    }

    private void shareIt(int score) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Mon score d'Android Quizz est " + mScore;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Score Android Quizz");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Partager via"));

    }

    //empêche de quitter la vue via le bouton retour
    @Override
    public void onBackPressed() {
        return;
    }
}
