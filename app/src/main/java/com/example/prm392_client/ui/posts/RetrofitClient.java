package com.example.prm392_client.ui.posts;

import com.example.prm392_client.ui.messages.ChatApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:5154/";
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
