package com.example.prm392_client.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.prm392_client.model.contact.Invitation;
import com.example.prm392_client.model.contact.InvitationDTO;
import com.example.prm392_client.ui.contacts.InvitationRepository;

import java.util.List;
// ... các import khác

public class InvitationViewModel extends ViewModel {
    private final InvitationRepository repository;

    private final MutableLiveData<List<Invitation>> _sentInvitations = new MutableLiveData<>();
    public LiveData<List<Invitation>> sentInvitations = _sentInvitations;

    private final MutableLiveData<List<Invitation>> _receivedInvitations = new MutableLiveData<>();
    public LiveData<List<Invitation>> receivedInvitations = _receivedInvitations;

    private final MutableLiveData<String> _memberInfor = new MutableLiveData<>();
    public LiveData<String> memberInfor = _memberInfor;

    public InvitationViewModel(InvitationRepository repository) {
        this.repository = repository;
    }

    public void loadSentInvitations(String memberId) {
        new Thread(() -> {
            try {
                List<Invitation> invitations = repository.getSentInvitations(memberId);
                _sentInvitations.postValue(invitations);

            } catch (Exception e) {
                _sentInvitations.postValue(null);
            }
        }).start();
    }

    public void loadReceivedInvitation(String memberId){
        new Thread(() -> {
            try {
                List<Invitation> invitations = repository.getReceivedRequest(memberId);
                _receivedInvitations.postValue(invitations);
            } catch (Exception e) {
                _receivedInvitations.postValue(null);
            }
        }).start();
    }

    public void acceptInvitation(InvitationDTO invitationDTO) {
        new Thread(() -> {
            try {
                boolean success = repository.acceptFriendRequest(invitationDTO);
                if (success){
                    loadReceivedInvitation(invitationDTO.getToID());
                }
            } catch (Exception e) {
            }
        }).start();
    }

    public void rejectInvitation(InvitationDTO invitationDTO) {
        new Thread(() -> {
            try {
                boolean success = repository.rejectFriendRequest(invitationDTO);
                if (success) {
                    loadReceivedInvitation(invitationDTO.getToID());
                }
            } catch (Exception e) {
            }
        }).start();
    }

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