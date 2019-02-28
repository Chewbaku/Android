package com.example.androidquizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ParsingActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://82.243.109.160/android/questions.json";

    private RequestQueue mRequestQueue;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GsonBuilder gsonBuilder = new GsonBuilder();
        this.mGson = gsonBuilder.create();

        this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        fetchPost();

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

            Question[] quizzQuestions = new Question[5];

            int random = new Random().nextInt(questions.size());
            for (int i = 0; i < 5; i++) {
                int position = (random + i) % questions.size();
                quizzQuestions[i] = questions.get(position);
            }

            Quizz quizz = new Quizz(quizzQuestions);

            for (Question question : quizz.getQuestions()) {
                Log.i("Json Parsing", question.toString());
            }

        }
    };

    private final Response.ErrorListener onQuestionsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("JSON Parsing", error.toString());
        }
    };

}
