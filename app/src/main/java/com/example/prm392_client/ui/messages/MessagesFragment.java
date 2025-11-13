package com.example.prm392_client.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.prm392_client.databinding.FragmentMessagesBinding;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentMessagesBinding binding;
    private ConversationAdapter adapter;
    private static final String MemberId = "6914b4d60a6237d6c93a4c0e";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new ConversationAdapter(MemberId);
        binding.recyclerConversations.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerConversations.setAdapter(adapter);

        binding.swipeRefresh.setOnRefreshListener(this);
        binding.swipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadConversations(); // TẢI MỚI MỖI LẦN MỞ
    }

    @Override
    public void onRefresh() {
        loadConversations();
    }

    private void loadConversations() {
        binding.swipeRefresh.setRefreshing(true);

        RetrofitClient.getApi().getConversations(MemberId).enqueue(new Callback<List<Conversation>>() {
            @Override
            public void onResponse(Call<List<Conversation>> call, Response<List<Conversation>> response) {
                binding.swipeRefresh.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Conversation>> call, Throwable t) {
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }
}