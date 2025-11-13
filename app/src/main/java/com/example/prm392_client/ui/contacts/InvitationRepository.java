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
        }
        catch (IOException e) {
            Log.e("Connect status", "Error: " + e.getMessage(), e);
            throw e;
        }
        return null;
    }
}
