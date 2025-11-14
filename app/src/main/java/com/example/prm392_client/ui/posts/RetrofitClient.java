package com.example.prm392_client.ui.posts;

import com.example.prm392_client.ui.messages.ChatApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://1f06lcq1-8000.asse.devtunnels.ms/";
    private static Retrofit retrofit;

    public static PostApi getApi(){
        if ((retrofit == null)){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(PostApi.class);
    }
}
