package com.syncode.moviecataglogv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.syncode.moviecataglogv3.adapter.ViewPagerAdapter;
import com.syncode.moviecataglogv3.fragment.MoviesFragment;
import com.syncode.moviecataglogv3.fragment.MoviesTvFragment;
import com.syncode.moviecataglogv3.repository.SharedPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ViewPagerAdapter viewPagerAdapter;

    private String[] title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
        setResource();
        if (title.length > 0 && setFragment().size() > 0) {
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), setFragment(), setTitle());
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
        SharedPreference sharedPreference = new SharedPreference(this);
        if(sharedPreference.getReferences().isEmpty()){
            sharedPreference.setPref("en-US");
        }
    }

    private List<String> setTitle() {
        return new ArrayList<>(Arrays.asList(title));
    }

    private void setResource() {
        title = getResources().getStringArray(R.array.list_tab_title);
    }

    private List<Fragment> setFragment() {
        List<Fragment> listFrag = new ArrayList<>();
        listFrag.add(new MoviesFragment());
        listFrag.add(new MoviesTvFragment());
        return listFrag;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m_language) {
            Intent gotoLang = new Intent(this, LanguageActivity.class);
            startActivity(gotoLang);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
