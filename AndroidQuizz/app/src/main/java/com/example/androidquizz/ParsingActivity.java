package com.example.androidquizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidquizz.models.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class ParsingActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://82.243.109.160/android/questions.json";

    private RequestQueue mRequestQueue;
    private Gson mGson;
    private Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GsonBuilder gsonBuilder = new GsonBuilder();
        this.mGson = gsonBuilder.create();

        this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        this.fetchPost();
    }

    private void fetchPost() {
        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT, onQuestionsLoaded, onQuestionsError);

        this.mRequestQueue.add(request);
    }

    private final Response.Listener<String> onQuestionsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Type questionType = new TypeToken<List<Question>>(){}.getType();

            List<Question> questions = mGson.fromJson(response, questionType);

            Bundle bundle = new Bundle();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                Question question;
                int position = random.nextInt(questions.size());
                question = questions.get(position);
                question.update();
                bundle.putSerializable("Question" + i, new Gson().toJson(question));
                questions.remove(position);
            }
            Intent intent = new Intent(ParsingActivity.this, QuizzActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    private final Response.ErrorListener onQuestionsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("JSON Parsing", error.toString());
        }
    };
}
