package com.example.prm392_client.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_client.model.contact.GroupDTO;
import com.example.prm392_client.model.contact.Invitation;
import com.example.prm392_client.model.contact.InvitationDTO;
import com.example.prm392_client.model.contact.MemberDTO;
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

    private final MutableLiveData<List<MemberDTO>> _friendsList = new MutableLiveData<>();
    public LiveData<List<MemberDTO>> friendsList = _friendsList;

    public InvitationViewModel(InvitationRepository repository) {
        this.repository = repository;
    }

    private final MutableLiveData<List<GroupDTO>> _groupList = new MutableLiveData<>();
    public LiveData<List<GroupDTO>> groupList = _groupList; // <-- Trường mà Fragment observe
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

    public void loadFriendList(String memberId){
        new Thread(() -> {
            try {
                List<MemberDTO> friends = repository.getFriendList(memberId);

                _friendsList.postValue(friends);
            } catch (Exception e) {
                _friendsList.postValue(null);
            }
        }).start();
    }

    public void loadGroupList(String memberId){
        new Thread(() -> {
            try {
                List<GroupDTO> groups = repository.getGroupList(memberId);
                _groupList.postValue(groups);
            } catch (Exception e) {
                _groupList.postValue(null);
            }
        }).start();
    }

}