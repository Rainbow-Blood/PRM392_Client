package com.example.prm392_client.ui.contacts;

import com.example.prm392_client.model.contact.GroupDTO;
import com.example.prm392_client.model.contact.Invitation;
import com.example.prm392_client.model.contact.InvitationDTO;
import com.example.prm392_client.model.contact.MemberDTO;
import com.example.prm392_client.model.response.GenericResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactAPI {
    @GET("api/Invitation/GetSentInvitationByMemberId/sent")
    Call<List<Invitation>> getSentInvitations(@Header("Authorization") String token);

    @GET("api/Invitation/GetReceivedInvitationByMemberId/receive")
    Call<List<Invitation>> getReceivedInvitations(@Header("Authorization") String token);

    @GET("api/Invitation/GetFriendsByMemberId/friends")
    Call<List<MemberDTO>> getFriendList(@Header("Authorization") String token);

    // GET /api/Invitation/groups/{memberId}
    @GET("api/Invitation/GetGroupListByMemberId/groups")
    Call<List<GroupDTO>> getGroupList(@Header("Authorization") String token);
    @POST("api/Invitation/SendInvitation")
    Call<Invitation> sendInvitation(
            @Body InvitationDTO request
    );

    @PUT("api/Invitation/AcceptInvitation")
    Call<GenericResponse> acceptInvitation(
            @Body InvitationDTO request
    );

    @DELETE("api/Invitation/RejectInvitation/delete")
    Call<GenericResponse> rejectInvitation(
            @Body InvitationDTO request
    );
}
