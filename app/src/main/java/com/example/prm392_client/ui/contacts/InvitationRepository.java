package com.example.prm392_client.ui.contacts;

import static android.util.Log.WARN;

import android.util.Log;

import com.example.prm392_client.model.contact.Invitation;

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
                return response.body();
            }
            return null;
        } catch (IOException e) {
            Log.e("Connect status", "Error: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public String getMemberInfor(String memberId){
        try{
            retrofit2.Response<List<Invitation>> response =
                    api.getMemberInfor(memberId).execute();
            if(response.isSuccessful() && response.body()!= null){
                Log.println(WARN,"Connect status", "Sucessfull");
                return response.body().toString();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
