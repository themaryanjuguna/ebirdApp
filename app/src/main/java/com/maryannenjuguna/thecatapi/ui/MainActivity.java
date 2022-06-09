package com.maryannenjuguna.thecatapi.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maryannenjuguna.thecatapi.R;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton infoBtn,downloadBtn,likeBtn, refreshBtn;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoBtn = findViewById(R.id.infoBtn);
        downloadBtn = findViewById(R.id.downloadBtn);
        likeBtn = findViewById(R.id.refreshBtn);
        refreshBtn = findViewById(R.id.likeBtn);

        //LikehButton
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Refresh Button is Clicked.", Toast.LENGTH_SHORT).show();

                //String defined in night/string.xml
                getImage(getResources().getString(R.string.api_url));
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Info is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Download Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Liked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getImage(String url) {

        //ExtractJsonData

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: " + response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(arrayRequest);


        //recyclerView = findViewById(R.id.recycleview);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Moves to the next activity
                Intent intent = new Intent(MainActivity.this, BreedsActivity.class);
                startActivity(intent);

            }


        });
    }
}