package com.example.prm392_client.ui.contacts;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class InvitationViewModelFactory implements ViewModelProvider.Factory {

    private final InvitationRepository repository;

    public InvitationViewModelFactory(InvitationRepository repository) {
        this.repository = repository;
    }

    // 2. Hàm tạo ViewModel
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InvitationViewModel.class)) {
            return (T) new InvitationViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}