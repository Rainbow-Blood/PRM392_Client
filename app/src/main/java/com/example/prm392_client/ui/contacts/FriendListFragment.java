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
import com.example.prm392_client.model.contact.FriendAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class FriendListFragment extends Fragment {

    private InvitationViewModel invitationViewModel;
    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;

    private final String token = getToken();


    public FriendListFragment() {
    }


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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_friend_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        friendAdapter = new FriendAdapter(new ArrayList<>());
        recyclerView.setAdapter(friendAdapter);

        observeFriendsList();

        invitationViewModel.loadFriendList(token);
    }

    private void observeFriendsList() {
        invitationViewModel.friendsList.observe(getViewLifecycleOwner(), friends -> {
            if (friends != null && !friends.isEmpty()) {
                friendAdapter.updateData(friends);
            } else {
                friendAdapter.updateData(Collections.emptyList());
            }
        });
    }
}