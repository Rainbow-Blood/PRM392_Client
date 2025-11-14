package com.example.prm392_client.ui.messages;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = MessagesFragment.CoreResource.BASE_URL + "api/";
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
