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
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactAPI {
    @GET("api/Invitation/GetSentInvitationByMemberId/{memberId}/sent")
    Call<List<Invitation>> getSentInvitations(@Path("memberId") String memberId);

    @GET("api/Invitation/GetReceivedInvitationByMemberId/{memberId}/receive")
    Call<List<Invitation>> getReceivedInvitations(@Path("memberId") String memberId);

    @GET("api/Invitation/GetFriendsByMemberId/friends/{memberId}")
    Call<List<MemberDTO>> getFriendList(@Path("memberId") String memberId);

    // GET /api/Invitation/groups/{memberId}
    @GET("api/Invitation/GetGroupListByMemberId/groups/{memberId}")
    Call<List<GroupDTO>> getGroupList(@Path("memberId") String memberId);
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
