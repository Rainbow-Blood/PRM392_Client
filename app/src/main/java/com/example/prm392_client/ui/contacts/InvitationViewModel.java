package com.example.prm392_client.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.prm392_client.model.contact.Invitation;
import com.example.prm392_client.ui.contacts.InvitationRepository;

import java.util.List;
// ... các import khác

public class InvitationViewModel extends ViewModel {
    private final InvitationRepository repository;

    private final MutableLiveData<List<Invitation>> _sentInvitations = new MutableLiveData<>();
    public LiveData<List<Invitation>> sentInvitations = _sentInvitations;

    private final MutableLiveData<String> _memberInfor = new MutableLiveData<>();
    public LiveData<String> memberInfor = _memberInfor;

    public InvitationViewModel(InvitationRepository repository) {
        this.repository = repository;
    }

    public void loadSentInvitations(String memberId) {
        new Thread(() -> {
            try {
                List<Invitation> invitations = repository.getSentInvitations(memberId);

                // Sử dụng postValue để cập nhật LiveData từ luồng nền
                _sentInvitations.postValue(invitations);

            } catch (Exception e) {
                // Gửi null hoặc list rỗng khi có lỗi
                _sentInvitations.postValue(null);
            }
        }).start();
    }

    // Tương tự cho hàm loadMemberInfor
    public void loadMemberInfor(String memberId){
        new Thread(() -> {
            try {
                String name = repository.getMemberInfor(memberId);
                _memberInfor.postValue(name);
            } catch (Exception e) {
                _memberInfor.postValue(null);
            }
        }).start();
    }
}