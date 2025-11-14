package com.example.prm392_client.ui.messages;

import com.example.prm392_client.ui.messages.models.Conversation;
import com.example.prm392_client.ui.messages.models.Message;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface ChatApi {
    @GET("Conversation/GetConversationsByMemberId/{memberId}")
    Call<List<Conversation>> getConversations(@Path("memberId") String memberId);

    @GET("Message/GetMessagesByConversationId/{conversationId}")
    Call<List<Message>> getMessages(@Path("conversationId") String conversationId);
}
