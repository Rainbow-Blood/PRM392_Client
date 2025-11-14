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
import com.example.prm392_client.model.contact.GroupAdapter; // Import Adapter mới
import com.example.prm392_client.model.contact.GroupDTO; // Import DTO

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GroupListFragment extends Fragment {

    private InvitationViewModel invitationViewModel;
    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;

    private final String currentUserId = "6914b4d60a6237d6c93a4c1d";

    public GroupListFragment() {
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
        // Inflate layout (fragment_group_list)
        return inflater.inflate(R.layout.fragment_group_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_group_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        groupAdapter = new GroupAdapter(new ArrayList<>());
        recyclerView.setAdapter(groupAdapter);
        observeGroupList();
        invitationViewModel.loadGroupList(currentUserId);
    }

    private void observeGroupList() {
        invitationViewModel.groupList.observe(getViewLifecycleOwner(), groups -> {
            if (groups != null && !groups.isEmpty()) {
                groupAdapter.updateData(groups);
            } else {
                groupAdapter.updateData(Collections.emptyList());
            }
        });
    }
}