package com.example.prm392_client.ui.contacts;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull ContactsFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FriendListFragment();
            case 1:
                return new GroupListFragment();
            case 2:
                return new FriendRequestFragment();
            default:
                return new FriendListFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
