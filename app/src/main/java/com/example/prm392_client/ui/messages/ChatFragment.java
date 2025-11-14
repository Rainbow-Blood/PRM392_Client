package com.example.prm392_client.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.prm392_client.R;
import com.example.prm392_client.databinding.FragmentChatBinding;
import com.example.prm392_client.ui.messages.models.Conversation;
import com.example.prm392_client.ui.messages.models.Message;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private Conversation conversation;
    private MessageAdapter adapter;
    private final String cmId = "6916b0f22f22c63d5bc25ec8";
    private final SignalRHelper signalR = SignalRHelper.get();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideBars();

        if (getArguments() == null || (conversation = getArguments().getParcelable("conversation")) == null) {
            Toast.makeText(requireContext(), "Lỗi: Không tìm thấy cuộc trò chuyện", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(this).navigateUp();
            return;
        }

        setupUI();
        loadMessages();
        setupSignalR();
    }

    private void hideBars() {
        requireActivity().findViewById(R.id.appBarLayout).setVisibility(View.GONE);
        requireActivity().findViewById(R.id.bottom_nav_view).setVisibility(View.GONE);
    }

    private void setupUI() {
        binding.toolbar.setTitle(conversation.getOtherMemberName(cmId));
        binding.toolbar.setNavigationOnClickListener(v -> NavHostFragment.findNavController(this).navigateUp());

        adapter = new MessageAdapter(cmId);
        binding.recyclerMessages.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerMessages.setAdapter(adapter);

        binding.btnSend.setOnClickListener(v -> sendMessage());
    }

    private void loadMessages() {
        RetrofitClient.getApi().getMessages(conversation.Id).enqueue(new Callback<List<Message>>() {
            @Override public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setData(response.body());
                    scrollToBottom();
                }
            }
            @Override public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(requireContext(), "Tải tin nhắn thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSignalR() {
        signalR.connect();

        // CHỈ SET LISTENER 1 LẦN CHO MỖI FRAGMENT
        signalR.setListener(msg -> requireActivity().runOnUiThread(() -> {
            adapter.addMessage(msg);
            scrollToBottom();
        }));

        signalR.join(conversation.Id);
    }

    private void sendMessage() {
        String content = binding.edtMessage.getText().toString().trim();
        if (content.isEmpty()) return;

        Message msg = new Message();
        msg.Content = content;
        msg.OwnerID = cmId;
        msg.ConversationID = conversation.Id;

        binding.edtMessage.setText("");
        signalR.send(msg);
    }

    private void scrollToBottom() {
        if (adapter.getItemCount() > 0) {
            binding.recyclerMessages.scrollToPosition(adapter.getItemCount() - 1);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (conversation != null) {
            signalR.leave(conversation.Id);
        }

        signalR.setListener(null);

        // Hiện lại thanh điều hướng
        requireActivity().findViewById(R.id.appBarLayout).setVisibility(View.VISIBLE);
        requireActivity().findViewById(R.id.bottom_nav_view).setVisibility(View.VISIBLE);

        binding = null;
    }
}