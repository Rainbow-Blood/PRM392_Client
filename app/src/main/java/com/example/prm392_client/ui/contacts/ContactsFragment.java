package com.example.prm392_client.ui.contacts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prm392_client.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ContactsFragment extends Fragment {
    TabLayout tablayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tablayout = view.findViewById(R.id.contact_tab_layout);
        viewPager2 = view.findViewById(R.id.contact_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        // THÊM: Sử dụng TabLayoutMediator để đồng bộ hóa và gán tiêu đề
        new TabLayoutMediator(tablayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Friends");
                    break;
                case 1:
                    tab.setText("Groups");
                    break;
                case 2:
                    tab.setText("Requests");
                    break;
            }
        }).attach();

        // BỎ đi listener cũ nếu dùng TabLayoutMediator (hoặc dùng nó để ghi đè)
        // viewPager2.setCurrentItem(tab.getPosition()); sẽ được xử lý tự động.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        return view;
    }
}