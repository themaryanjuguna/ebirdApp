package com.maryannenjuguna.thecatapi.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.adapters.RecyclerViewAdapter;
import com.maryannenjuguna.thecatapi.models.TheCatBreedSearchResponse;
import com.maryannenjuguna.thecatapi.network.theCatApi;
import com.maryannenjuguna.thecatapi.network.theCatApiClient;
import com.maryannenjuguna.thecatapi.ui.loginsignup.LoginActivity;
import com.maryannenjuguna.thecatapi.ui.loginsignup.RegisterActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ImageButton likeImgBtn;

    DatabaseReference favouriteref;
    FirebaseDatabase database=FirebaseDatabase.getInstance();

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

                if (response.isSuccessful()) {
                    catBreeds = response.body();
                    adapter = new RecyclerViewAdapter(catBreeds, MainActivity.this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}

