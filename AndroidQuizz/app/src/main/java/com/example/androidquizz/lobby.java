package com.example.androidquizz;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidquizz.database.DatabaseHelper;
import com.example.androidquizz.models.Statistics;
import com.example.androidquizz.models.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class lobby extends AppCompatActivity {

    TextView lobbyLogin;
    final Executor executor = Executors.newSingleThreadExecutor();
    private DatabaseHelper dbh = DatabaseHelper.getInstance();

    TextView txtNbPartiesJouees;
    TextView txtNbQuestionsPosees;
    TextView txtNbReponsesCorrectes;
    TextView txtTauxReussite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                User user = dbh.getUserDao().getUser(0);
                Statistics statistics = dbh.getStatisticsDao().getStatistics(user.getId());

                lobbyLogin = findViewById(R.id.lobbyLogin);
                lobbyLogin.setText(user.getLogin().toString());

                txtNbPartiesJouees = findViewById(R.id.intNbPartiesJouees);
                txtNbQuestionsPosees = findViewById(R.id.intNbQuestionPosees);
                txtNbReponsesCorrectes = findViewById(R.id.intNbReponsesCorrectes);
                txtTauxReussite = findViewById(R.id.intPourcentageBonnesReponses);

                int nbQuestionsPosees = statistics.getNbPlayedAnswers();

                int nbPartiesJouees = 0;
                if(nbQuestionsPosees != 0) {
                    nbPartiesJouees = nbQuestionsPosees / 5;
                } else {
                    nbPartiesJouees = 0;
                }
                int nbReponsesCorrectes = statistics.getNbGoodAnswers();

                int tauxReussite = 0;
                if((nbQuestionsPosees != 0) && (nbQuestionsPosees != 0) ) {
                    tauxReussite = nbReponsesCorrectes / nbQuestionsPosees;
                } else {
                    tauxReussite = 0;
                }

                txtNbQuestionsPosees.setText(String.valueOf(nbQuestionsPosees));
                txtNbPartiesJouees.setText(String.valueOf(nbPartiesJouees));
                txtNbReponsesCorrectes.setText(String.valueOf(nbReponsesCorrectes));
                txtTauxReussite.setText(String.valueOf(tauxReussite));
            }
        });

        Button buttonStart;
        buttonStart = (Button) findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lobby.this, ParsingActivity.class);
                startActivity(intent);

            }
        });
    }
    
    //empêche de quitter la vue via le bouton retour
    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.actionLogout) {
            // Finish this activity, and go back to LoginActivity
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
