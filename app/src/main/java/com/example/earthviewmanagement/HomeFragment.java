package com.example.earthviewmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {
    View myFragment;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = myFragment.findViewById(R.id.tabLayout);
        viewPager = myFragment.findViewById(R.id.viewpager);
        return myFragment;

        @Override
        public void onActivityCreated(@NonNull Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
        }
    }
}