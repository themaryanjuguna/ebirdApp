package com.maryannenjuguna.thecatapi.network;

import com.maryannenjuguna.thecatapi.models.Image;
import com.maryannenjuguna.thecatapi.models.TheCatBreedSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface theCatApi {
    @GET("breeds")
    Call<List<TheCatBreedSearchResponse>> getBreeds();

    @GET("image")
    Call<Image> getImage();
}
