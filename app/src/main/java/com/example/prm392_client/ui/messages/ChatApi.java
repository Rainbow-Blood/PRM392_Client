package com.example.prm392_client.ui.messages;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChatApi {
    @GET("Conversation/GetConversationsByMemberId/{memberId}")
    Call<List<Conversation>> getConversations(@Path("memberId") String memberId);
}
