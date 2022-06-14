package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.adapters.RecyclerViewAdapter;
import com.maryannenjuguna.thecatapi.models.Datum;
import com.maryannenjuguna.thecatapi.models.TheCatResponse;
import com.maryannenjuguna.thecatapi.network.theCatApi;
import com.maryannenjuguna.thecatapi.network.theCatApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   RecyclerView recyclerView;
   RecyclerViewAdapter adapter;
   RecyclerView.LayoutManager layoutManager;

    List<Datum> catBreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.viewRecycler);
        theCatApi maryanne = theCatApiClient.getClient();
        Call<TheCatResponse> maryannecats = maryanne.getBreeds();
        maryannecats.enqueue(new Callback<TheCatResponse>() {
            @Override
            public void onResponse(Call<TheCatResponse> call, Response<TheCatResponse> response) {
                if (response.isSuccessful()){
                    catBreeds = response.body().getData();
                    adapter= new RecyclerViewAdapter(catBreeds, MainActivity.this);
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<TheCatResponse> call, Throwable t) {

            }
        });

    }
}