package com.example.prm392_client.ui.contacts;

import static android.util.Log.WARN;

import android.util.Log;

import com.example.prm392_client.model.contact.Invitation;
import com.example.prm392_client.model.contact.InvitationDTO;
import com.example.prm392_client.model.response.GenericResponse;

import java.io.IOException;
import java.util.List;

public class InvitationRepository {
    private final ContactAPI api;

    public InvitationRepository(){
        this.api = RetrofitClient.getContactAPI();
    }

    public List<Invitation> getSentInvitations(String memberId) throws IOException{
        try{
            retrofit2.Response<List<Invitation>> response =
                    api.getSentInvitations(memberId).execute();
            if(response.isSuccessful() && response.body()!= null){
                Log.println(WARN,"Connect status", "Sucessfull");
                return response.body();
            }
            return null;
        }
        catch (IOException e) {
            Log.e("Connect status", "Error: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Invitation> getReceivedRequest(String memberId){
        try{
            retrofit2.Response<List<Invitation>> response =
                    api.getReceivedInvitations(memberId).execute();
            if(response.isSuccessful() && response.body()!= null){
                Log.println(WARN,"Connect status", "Sucessfull");
                Log.println(WARN,"Connect status", response.body().toString());
                return response.body();
            }
            return null;
        } catch (IOException e) {
            Log.e("Connect status", "Error: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public boolean acceptFriendRequest(InvitationDTO invitationDTO){
        try{
            retrofit2.Response<GenericResponse> response =
                    api.acceptInvitation(invitationDTO).execute();
            return response.isSuccessful();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean rejectFriendRequest(InvitationDTO invitationDTO){
        try{
            retrofit2.Response<GenericResponse> response = api.rejectInvitation(invitationDTO).execute();
            return response.isSuccessful();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMemberInfor(String memberId){
        try{
            retrofit2.Response<String> response =
                    api.getMemberInfor(memberId).execute();
            if(response.isSuccessful() && response.body()!= null){
                Log.println(WARN,"Connect status", "Sucessfull");
                return response.body();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
