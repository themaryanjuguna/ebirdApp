package com.maryannenjuguna.thecatapi.network;

import static com.maryannenjuguna.thecatapi.Constants.THECATAPI_BASE_URL;
import static com.maryannenjuguna.thecatapi.Constants.THECATAPI_KEY;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class theCatApiClient {

    private static Retrofit retrofit = null;

    public static theCatApi getClient(){

        if (retrofit == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
                                    .addHeader("Authorization", THECATAPI_KEY)
                            .build();
                            return chain.proceed(newRequest);

                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(THECATAPI_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(theCatApi.class);
    }

}
