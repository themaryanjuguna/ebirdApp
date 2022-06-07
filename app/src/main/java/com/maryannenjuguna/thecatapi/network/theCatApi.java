package com.maryannenjuguna.thecatapi.network;

import com.maryannenjuguna.thecatapi.models.TheCatBreedSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface theCatApi {
    @GET("breeds")
    Call<TheCatBreedSearchResponse> getbreeds(
    );
}
