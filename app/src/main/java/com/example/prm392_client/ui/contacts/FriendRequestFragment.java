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
import com.example.prm392_client.model.contact.Invitation;
import com.example.prm392_client.model.contact.InvitationAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendRequestFragment extends Fragment {
    private InvitationViewModel invitationViewModel;
    private RecyclerView recyclerView;
    private InvitationAdapter invitationAdapter;
    private final String currentUserId = "6914b4d60a6237d6c93a4c1d";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InvitationRepository repository = new InvitationRepository();

        invitationViewModel = new ViewModelProvider(this,
                new InvitationViewModelFactory(repository))
                .get(InvitationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 2. Ánh xạ View và Thiết lập RecyclerView
        // Thay R.id.recyclerView_requests bằng ID thực tế của bạn
        recyclerView = view.findViewById(R.id.rv_friend_request);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        invitationAdapter = new InvitationAdapter(new ArrayList<>(), invitationViewModel);
        recyclerView.setAdapter(invitationAdapter);

        // 4. Quan sát LiveData (Bind dữ liệu)
        observeReceivedInvitations();

        // 5. Kích hoạt tải dữ liệu
        invitationViewModel.loadReceivedInvitation(currentUserId);
    }

    private void observeReceivedInvitations() {
        // Quan sát LiveData chứa danh sách lời mời đã nhận
        invitationViewModel.receivedInvitations.observe(getViewLifecycleOwner(), invitations -> {
            // LiveData sẽ kích hoạt khi dữ liệu được tải hoặc cập nhật
            if (invitations != null && !invitations.isEmpty()) {
                // Cập nhật Adapter khi dữ liệu thành công
                invitationAdapter.updateData(invitations);
            } else {
                invitationAdapter.updateData(Collections.emptyList());
            }
        });
    }
}