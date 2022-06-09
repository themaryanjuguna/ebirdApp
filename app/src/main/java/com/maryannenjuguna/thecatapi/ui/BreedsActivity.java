package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.TheCatApiBreedsArrayAdapter;
import com.maryannenjuguna.thecatapi.models.Image;
import com.maryannenjuguna.thecatapi.models.TheCatBreedSearchResponse;
import com.maryannenjuguna.thecatapi.network.theCatApi;
import com.maryannenjuguna.thecatapi.network.theCatApiClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedsActivity extends AppCompatActivity {
    private static final String TAG = BreedsActivity.class.getSimpleName();

    Button infoBtn,downloadBtn,likeBtn,moreFacts,refreshBtn;
    ImageView imageView;
    TextView name, description, temperament, life_span,errorTextView;


    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.breedsTextView) TextView mBreedTextView;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.description) TextView mDescription;
    @BindView(R.id.temperament) TextView mTemperament;
    @BindView(R.id.life_span) TextView mLife_span;
   /* @BindView(R.id.infoBtn) Button mInfoBtn;
    @BindView(R.id.downloadBtn) Button mDownloadBtn;
    @BindView(R.id.likeBtn) Button mLikeBtn;
    @BindView(R.id.moreFacts) Button mMoreFacts;
    @BindView(R.id.refreshBtn) Button mRefreshBtn;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
        ButterKnife.bind(this);


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
                    String content = "";

                    //cats Name
                    mName.setText(theCatBreedSearchResponse.getName());
                    mName.append(content);

                    //Cats Description
                    mDescription.setText(theCatBreedSearchResponse.getDescription());
                    mDescription.append(content);

                    //Cats temperament
                    mTemperament.setText(theCatBreedSearchResponse.getTemperament());
                    mTemperament.append(content);

                    //Cats Life Span
                    mLife_span.setText(theCatBreedSearchResponse.getLifeSpan());
                    mLife_span.append(content);


                    /*content += theCatBreedSearchResponse.getClass().getResource("url") + "\n";
                    content += "Name: " + theCatBreedSearchResponse.getName() + "\n";
                    content += "Life Span: " + theCatBreedSearchResponse.getLifeSpan() + "\n";
                    content += "Temperament: " + theCatBreedSearchResponse.getTemperament() + "\n";
                    content += "Description: " + theCatBreedSearchResponse.getDescription() + "\n\n";*/



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

}