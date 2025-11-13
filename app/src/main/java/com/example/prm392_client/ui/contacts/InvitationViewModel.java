package com.example.prm392_client.ui.contacts;

import androidx.lifecycle.ViewModel;

import com.example.prm392_client.model.contact.Invitation;

import java.io.IOException;
import java.util.List;

public class InvitationViewModel extends ViewModel {
    private final InvitationRepository repository;
    public InvitationViewModel() {
        // Khởi tạo Repository
        this.repository = new InvitationRepository();
    }

    public void loadSentInvitations(String memberId) {
        new Thread(() -> {
            try {
                List<Invitation> invitations = repository.getSentInvitations(memberId);

                if (invitations != null) {
                    System.out.println("Tải thành công " + invitations.size() + " lời mời.");
                } else {
                    System.out.println("Lỗi tải lời mời.");
                }
            } catch (IOException e) {
                System.err.println("Lỗi mạng: " + e.getMessage());
            }
        }).start();
    }
}
