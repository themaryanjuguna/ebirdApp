package com.maryannenjuguna.thecatapi.network;

import com.maryannenjuguna.thecatapi.models.TheCatResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface theCatApi {
    @GET("api/v1")
    Call<TheCatResponse> getBreeds();
}
