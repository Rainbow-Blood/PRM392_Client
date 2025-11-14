package com.example.prm392_client.ui.contacts;

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

    private final String currentUserId = "6914b4d60a6237d6c93a4c1d";


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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 2. Ánh xạ View và Thiết lập RecyclerView
        // Thay R.id.recyclerView_friends bằng ID thực tế của bạn trong fragment_friend_list.xml
        recyclerView = view.findViewById(R.id.rv_friend_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Khởi tạo Adapter và gán vào RecyclerView
        friendAdapter = new FriendAdapter(new ArrayList<>());
        recyclerView.setAdapter(friendAdapter);

        observeFriendsList();

        invitationViewModel.loadFriendList(currentUserId);
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