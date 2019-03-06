package com.example.androidquizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class QuizzActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://82.243.109.160/android/questions.json";

    private RequestQueue mRequestQueue;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GsonBuilder gsonBuilder = new GsonBuilder();
        this.mGson = gsonBuilder.create();

        this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        this.fetchPost();
    }

    private void fetchPost() {
        StringRequest request = new StringRequest(Request.Method.GET,ENDPOINT, onQuestionsLoaded, onQuestionsError);
        this.mRequestQueue.add(request);
    }
    
    private final Response.Listener<String> onQuestionsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Type questionType = new TypeToken<List<Question>>(){}.getType();

            List<Question> questions = mGson.fromJson(response, questionType);

            Question[] questions1 = new Question[5];
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int position = random.nextInt(questions.size());
                questions1[i] = questions.get(position);
                questions1[i].update();
                questions.remove(position);
            }

            launchQuizz(questions1);

        }


    };

    private final Response.ErrorListener onQuestionsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("JSON Parsing", error.toString());
        }
    };

    private void launchQuizz(Question[] questions) {
        for (Question question : questions) {
            Intent intent = new Intent(QuizzActivity.this, QuestionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Question", new Gson().toJson(question));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
