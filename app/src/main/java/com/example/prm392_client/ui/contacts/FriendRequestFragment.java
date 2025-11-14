package com.example.prm392_client.ui.contacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prm392_client.R;
import com.example.prm392_client.model.contact.Invitation;
import com.example.prm392_client.model.contact.InvitationAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendRequestFragment extends Fragment {
    private InvitationViewModel invitationViewModel;
    private RecyclerView recyclerView;
    private InvitationAdapter invitationAdapter;
    private final String token = getToken();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InvitationRepository repository = new InvitationRepository();

        invitationViewModel = new ViewModelProvider(this,
                new InvitationViewModelFactory(repository))
                .get(InvitationViewModel.class);
    }

    private String getToken() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString("USER_TOKEN", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_friend_request);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        invitationAdapter = new InvitationAdapter(new ArrayList<>(), invitationViewModel);
        recyclerView.setAdapter(invitationAdapter);

        observeReceivedInvitations();
        invitationViewModel.loadReceivedInvitation(token);
    }

    private void observeReceivedInvitations() {
        invitationViewModel.receivedInvitations.observe(getViewLifecycleOwner(), invitations -> {
            if (invitations != null && !invitations.isEmpty()) {
                invitationAdapter.updateData(invitations);
            } else {
                invitationAdapter.updateData(Collections.emptyList());
            }
        });
    }
}