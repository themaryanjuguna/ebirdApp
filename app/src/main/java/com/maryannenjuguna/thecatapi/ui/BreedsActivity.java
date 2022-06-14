package com.maryannenjuguna.thecatapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.maryannenjuguna.thecatapi.R;
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

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.breedsTextView) TextView mBreedTextView;
    //@BindView(R.id.progressBar) ProgressBar mProgressBar;
   /* @BindView(R.id.listView) ListView mListView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
        ButterKnife.bind(this);


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