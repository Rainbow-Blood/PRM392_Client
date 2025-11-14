package com.example.prm392_client.ui.messages;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://1f06lcq1-8000.asse.devtunnels.ms/api/";
    private static ChatApi api;

    public static ChatApi getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(ChatApi.class);
        }
        return api;
    }
}
