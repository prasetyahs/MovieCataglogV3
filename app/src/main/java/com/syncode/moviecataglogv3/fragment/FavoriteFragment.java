package com.syncode.moviecataglogv3.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.syncode.moviecataglogv3.R;
import com.syncode.moviecataglogv3.adapter.ViewPagerAdapter;
import com.syncode.moviecataglogv3.fragment.favoritefragment.MovieFavoriteFragment;
import com.syncode.moviecataglogv3.fragment.favoritefragment.MovieTVFavoriteFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoriteFragment extends Fragment {


    private String[] title;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        setResource();
        if (title.length > 0 && setFragment().size() > 0) {
            if (getFragmentManager() != null) {
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), setFragment(), setTitle());
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        }
    }


    private List<String> setTitle() {
        return new ArrayList<>(Arrays.asList(title));
    }

    private void setResource() {
        title = getResources().getStringArray(R.array.title_tab);
    }

    private List<Fragment> setFragment() {
        List<Fragment> listFrag = new ArrayList<>();
        listFrag.add(new MovieFavoriteFragment());
        listFrag.add(new MovieTVFavoriteFragment());
        return listFrag;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

}
