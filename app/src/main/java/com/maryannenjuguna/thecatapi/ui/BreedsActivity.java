package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.breedsTextView)
    TextView mBreedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
        ButterKnife.bind(this);

       /* mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
*/
        theCatApi client = theCatApiClient.getClient();
        Call<List<TheCatBreedSearchResponse>> call = client.getBreeds();

        call.enqueue(new Callback<List<TheCatBreedSearchResponse>>() {
            @Override
            public void onResponse(Call<List<TheCatBreedSearchResponse>> call, Response<List<TheCatBreedSearchResponse>> response) {

                if (!response.isSuccessful()) {
                    mErrorTextView.setText("Code: " + response.code());
                    return;
                }

                List<TheCatBreedSearchResponse> theCatBreedSearchResponses = response.body();

                for (TheCatBreedSearchResponse theCatBreedSearchResponse : theCatBreedSearchResponses) {
                    String content = "";
                    content += theCatBreedSearchResponse.getClass().getResource("url") + "\n";
                    content += "Name: " + theCatBreedSearchResponse.getName() + "\n";
                    content += "Life Span: " + theCatBreedSearchResponse.getLifeSpan() + "\n";
                    content += "Temperament: " + theCatBreedSearchResponse.getTemperament() + "\n";
                    content += "Description: " + theCatBreedSearchResponse.getDescription() + "\n\n";

                    mBreedTextView.append(content);

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

