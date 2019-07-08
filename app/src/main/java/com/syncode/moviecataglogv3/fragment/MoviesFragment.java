package com.syncode.moviecataglogv3.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syncode.moviecataglogv3.R;
import com.syncode.moviecataglogv3.adapter.RecycleMoviesAdapter;
import com.syncode.moviecataglogv3.api.Constanta;
import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.repository.SharedPreference;
import com.syncode.moviecataglogv3.viewmodel.MoviesViewModel;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {


    public MoviesFragment() {

    }


    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;

    private SharedPreference sharedPreference;

    private String lang;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progressBar);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        sharedPreference = new SharedPreference(this.getActivity());
        moviesViewModel.setMovies("movie", sharedPreference.getReferences(), Constanta.API_KEY);
        moviesViewModel.getMovies().observe(this, getMovies);
        if (moviesViewModel.getMovies().getValue() == null) {
            moviesViewModel.getError().observe(this, getErrorMessage);
        }
        lang = sharedPreference.getReferences();
        progressBar.setVisibility(View.VISIBLE);

    }

    private Observer<ArrayList<Movies>> getMovies = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movies) {
            if (movies.size() > 0) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            RecycleMoviesAdapter recycleMoviesAdapter = new RecycleMoviesAdapter(movies, getContext());
            RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(lm);
            recyclerView.setAdapter(recycleMoviesAdapter);
            recycleMoviesAdapter.notifyDataSetChanged();
        }
    };

    private Observer<String> getErrorMessage = new Observer<String>() {
        @Override
        public void onChanged(String message) {
            if (message != null) {
                progressBar.setVisibility(View.GONE);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setPositiveButton(getResources().getString(R.string.refresh), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moviesViewModel.setMovies("movie", new SharedPreference(MoviesFragment.this.getActivity()).getReferences(), Constanta.API_KEY);
                        moviesViewModel.getMovies().observe(MoviesFragment.this, getMovies);
                        progressBar.setVisibility(View.VISIBLE);

                    }
                });
                builder.setCancelable(false);
                builder.setTitle(message);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!lang.equals(sharedPreference.getReferences())) {
            recyclerView.setVisibility(View.GONE);
            moviesViewModel.setMovies("movie", sharedPreference.getReferences(), Constanta.API_KEY);
            moviesViewModel.getMovies().observe(this, getMovies);
            if (moviesViewModel.getMovies().getValue() == null) {
                moviesViewModel.getError().observe(this, getErrorMessage);
            }
            lang = sharedPreference.getReferences();
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
