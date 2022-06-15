package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.adapters.RecyclerViewAdapter;
import com.maryannenjuguna.thecatapi.models.TheCatBreedSearchResponse;
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

    List<TheCatBreedSearchResponse> catBreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.viewRecycler);
        theCatApi feral = theCatApiClient.getClient();
        Call<List<TheCatBreedSearchResponse>> catFamily = feral.getBreeds();
        catFamily.enqueue(new Callback<List<TheCatBreedSearchResponse>>() {
            @Override
            public void onResponse(Call<List<TheCatBreedSearchResponse>> call, Response<List<TheCatBreedSearchResponse>> response) {

                if (response.isSuccessful()){
                    catBreeds = response.body();
                    adapter= new RecyclerViewAdapter(catBreeds, MainActivity.this);
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<List<TheCatBreedSearchResponse>> call, Throwable t) {

            }
        });


    }
}
