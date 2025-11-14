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
import com.example.prm392_client.ui.messages.models.Conversation;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentMessagesBinding binding;
    private ConversationAdapter adapter;
    private static final String MEMBER_ID = "6916b0f22f22c63d5bc25ec8";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new ConversationAdapter(MEMBER_ID);
        binding.recyclerConversations.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerConversations.setAdapter(adapter);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    @Override public void onResume() {
        super.onResume();
        load();
    }

    @Override public void onRefresh() { load(); }

    private void load() {
        binding.swipeRefresh.setRefreshing(true);
        RetrofitClient.getApi().getConversations(MEMBER_ID).enqueue(new Callback<List<Conversation>>() {
            @Override public void onResponse(Call<List<Conversation>> call, Response<List<Conversation>> response) {
                binding.swipeRefresh.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setData(response.body());
                }
            }
            @Override public void onFailure(Call<List<Conversation>> call, Throwable t) {
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }
    public class CoreResource {
        public static final String BASE_URL = "https://1f06lcq1-8000.asse.devtunnels.ms/";
        public static final String BASE_URL_NON_HTTPS = "http://10.0.2.2:8000/api/";
    }
}