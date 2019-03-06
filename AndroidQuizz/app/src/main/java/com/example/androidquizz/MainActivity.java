package com.example.androidquizz;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.example.androidquizz.models.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    //public RecyclerView mRecyclerView;
    //public List<User> mUser;


    TextView loginEditText;
    Button loginButton;
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mRecyclerView = (RecyclerView) findViewById(R.id.);

        loginEditText = findViewById(R.id.loginEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setLogin(loginEditText.getText().toString());
                Intent intent = new Intent(MainActivity.this, lobby.class);
                startActivity(intent);

            }
        });



    }


}
