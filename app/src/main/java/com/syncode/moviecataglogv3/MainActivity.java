package com.syncode.moviecataglogv3;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syncode.moviecataglogv3.fragment.FavoriteFragment;
import com.syncode.moviecataglogv3.fragment.MoviesFragment;
import com.syncode.moviecataglogv3.fragment.MoviesTvFragment;
import com.syncode.moviecataglogv3.localdata.SharedPreference;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private SharedPreference sharedPreference;
    FrameLayout containerFragment;
    BottomNavigationView bottomNavigationView;

    private String lang;

    final Fragment fragMovie = new MoviesFragment();
    final Fragment fragmentMovieTv = new MoviesTvFragment();
    final Fragment fragmentFavorite = new FavoriteFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreference = new SharedPreference(this);
        setLanguage(sharedPreference.getReferences("lang2"));
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
        containerFragment = findViewById(R.id.container);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        lang = sharedPreference.getReferences("lang2");
        if (sharedPreference.getReferences("lang").isEmpty() && sharedPreference.getReferences("lang2").isEmpty()) {
            sharedPreference.setPref("en-US", "en");
        }
        if (savedInstanceState != null) {
            switch (savedInstanceState.getInt("fragState")) {
                case R.id.tabMovie:
                    active = fragMovie;
                    fm.beginTransaction().add(R.id.container, fragmentFavorite, fragmentFavorite.getClass().getSimpleName()).hide(fragmentFavorite).commit();
                    fm.beginTransaction().add(R.id.container, fragmentMovieTv, fragmentMovieTv.getClass().getSimpleName()).hide(fragmentMovieTv).commit();
                    break;
                case R.id.tabTv:
                    active = fragmentMovieTv;
                    fm.beginTransaction().add(R.id.container, fragMovie, fragMovie.getClass().getSimpleName()).hide(fragMovie).commit();
                    fm.beginTransaction().add(R.id.container, fragmentFavorite, fragmentFavorite.getClass().getSimpleName()).hide(fragmentFavorite).commit();
                    break;
                case R.id.favorite:
                    active = fragmentFavorite;
                    fm.beginTransaction().add(R.id.container, fragMovie, fragMovie.getClass().getSimpleName()).hide(fragMovie).commit();
                    fm.beginTransaction().add(R.id.container, fragmentMovieTv, fragmentMovieTv.getClass().getSimpleName()).hide(fragmentMovieTv).commit();
                    break;
            }
            fm.beginTransaction().add(R.id.container, active, active.getClass().getSimpleName()).commit();
        }else{
            fm.beginTransaction().add(R.id.container, fragmentFavorite, fragmentFavorite.getClass().getSimpleName()).hide(fragmentFavorite).commit();
            fm.beginTransaction().add(R.id.container, fragmentMovieTv, fragmentMovieTv.getClass().getSimpleName()).hide(fragmentMovieTv).commit();
            fm.beginTransaction().add(R.id.container, fragMovie, fragMovie.getClass().getSimpleName()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tabMovie:
                        fm.beginTransaction().hide(active).show(fragMovie).commit();
                        active = fragMovie;
                        return true;
                    case R.id.tabTv:
                        fm.beginTransaction().hide(active).show(fragmentMovieTv).commit();
                        active = fragmentMovieTv;
                        return true;
                    case R.id.favorite:
                        fm.beginTransaction().hide(active).show(fragmentFavorite).commit();
                        active = fragmentFavorite;
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("fragState", bottomNavigationView.getSelectedItemId());
        super.onSaveInstanceState(outState);
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
    protected void onResume() {
        super.onResume();
        if (!lang.equals(sharedPreference.getReferences("lang2"))) {
            for (Fragment fragment : fm.getFragments()) {
                fm.beginTransaction().remove(fragment).commit();
            }
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void setLanguage(String language) {
        Locale locale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration configuration = res.getConfiguration();
        configuration.locale = locale;
        res.updateConfiguration(configuration, dm);
    }
}
