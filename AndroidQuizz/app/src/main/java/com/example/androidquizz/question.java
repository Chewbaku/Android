package com.example.androidquizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    //empêche de quitter la vue via le bouton retour
    @Override
    public void onBackPressed() {
        return;
    }
}
