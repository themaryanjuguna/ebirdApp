package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.TheCatApiBreedsArrayAdapter;
import com.maryannenjuguna.thecatapi.models.Image;
import com.maryannenjuguna.thecatapi.models.TheCatBreedSearchResponse;
import com.maryannenjuguna.thecatapi.network.theCatApi;
import com.maryannenjuguna.thecatapi.network.theCatApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedsActivity extends AppCompatActivity {

    FloatingActionButton infoBtn,downloadBtn,likeBtn, refreshBtn;
    ImageView imageView;
    TextView name, description, temperament, life_span;
    Button moreFacts;

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.breedsTextView) TextView mBreedTextView;
    @BindView(R.id.moreFacts) Button mMoreFacts;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.description) TextView mDescription;
    @BindView(R.id.temperament) TextView mTemperament;
    @BindView(R.id.life_span) TextView mLife_Span;

    //@BindView(R.id.progressBar) ProgressBar mProgressBar;
   /* @BindView(R.id.listView) ListView mListView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
        ButterKnife.bind(this);
        Intent  data = getIntent();


        infoBtn = findViewById(R.id.infoBtn);
        downloadBtn = findViewById(R.id.downloadBtn);
        likeBtn = findViewById(R.id.likeBtn);
        refreshBtn = findViewById(R.id.refreshBtn);
        imageView = findViewById(R.id.imageView);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        temperament = findViewById(R.id.temperament);
        life_span =findViewById(R.id.life_span);
        moreFacts = findViewById(R.id.moreFacts);


        ///

        //set data
        name.setText(data.getStringExtra("name"));
        description.setText(data.getStringExtra("description"));
        temperament.setText(data.getStringExtra("temperament"));
        life_span.setText(data.getStringExtra("life_span"));


        Picasso.get().load(data.getStringExtra("imageUrl")).into(imageView);

        moreFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri catUri = Uri.parse(data.getStringExtra("wikipedia_url"));
                Intent browser = new Intent(Intent.ACTION_VIEW,catUri);
                startActivity(browser);
            }
        });


        ////

        theCatApi client = theCatApiClient.getClient();
        Call<List<TheCatBreedSearchResponse>> call = client.getBreeds();

        call.enqueue(new Callback<List<TheCatBreedSearchResponse>>() {
            @Override
            public void onResponse(Call<List<TheCatBreedSearchResponse>> call, Response<List<TheCatBreedSearchResponse>> response) {

                if(!response.isSuccessful()){
                    mErrorTextView.setText("Code: " + response.code());
                    return;
                }

                List<TheCatBreedSearchResponse> theCatBreedSearchResponses = response.body();

                for(TheCatBreedSearchResponse theCatBreedSearchResponse : theCatBreedSearchResponses){
                   /* String content = "";
                    content += theCatBreedSearchResponse.getClass().getResource("url") + "\n";
                    content += "Name: " + theCatBreedSearchResponse.getName() + "\n";
                    content += "Life Span: " + theCatBreedSearchResponse.getLifeSpan() + "\n";
                    content += "Temperament: " + theCatBreedSearchResponse.getTemperament() + "\n";
                    content += "Description: " + theCatBreedSearchResponse.getDescription() + "\n\n";

                    mBreedTextView.append(content);*/

                    Intent i = new Intent(getApplicationContext(), CatInfoActivity.class);
                    i.putExtra("name",theCatBreedSearchResponse.getName());
                    i.putExtra("description",theCatBreedSearchResponse.getDescription());
                    i.putExtra("temperament",theCatBreedSearchResponse.getTemperament());
                    i.putExtra("life_span",theCatBreedSearchResponse.getLifeSpan());
                    i.putExtra("wikipedia_url",theCatBreedSearchResponse.getWikipediaUrl());
                    Picasso.get().load(data.getStringExtra("imageUrl")).into(imageView);

                    startActivity(i);

                }
            }

            @Override
            public void onFailure(Call<List<TheCatBreedSearchResponse>> call, Throwable t) {
                mErrorTextView.setText(t.getMessage());
            }
        });

    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

/*    private void showRestaurants() {
        mListView.setVisibility(View.VISIBLE);
        mBreedTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }*/
}