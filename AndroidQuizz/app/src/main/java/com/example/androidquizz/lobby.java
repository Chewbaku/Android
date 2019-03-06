package com.example.androidquizz;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class lobby extends AppCompatActivity {

    TextView lobbyLogin;
    TextView blinkTextView;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        lobbyLogin = findViewById(R.id.lobbyLogin);
        lobbyLogin.setText("Faut aller le chercher dans la BDD");

        Button buttonStart;
        buttonStart = (Button) findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lobby.this, QuizzActivity.class);
                startActivity(intent);

            }
        });
        blinkTextView = (TextView)findViewById(R.id.textWelcome);
        ObjectAnimator animator = ObjectAnimator.ofInt(blinkTextView, "backgroundColor", Color.YELLOW);
        animator.setDuration(500);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatMode(Animation.REVERSE);
        animator.setRepeatMode(Animation.INFINITE);
        animator.start();


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
