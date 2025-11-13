package com.example.prm392_client.ui.contacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prm392_client.R;
import com.example.prm392_client.model.contact.Invitation;

import java.util.List;

public class FriendRequestFragment extends Fragment {
    private InvitationViewModel invitationViewModel;
    private RecyclerView recyclerView;
//    private InvitationAdapter invitationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String memberId = "6914b4d60a6237d6c93a4c1d";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_request, container, false);
    }
}