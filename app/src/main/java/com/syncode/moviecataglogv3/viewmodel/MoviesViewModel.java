package com.syncode.moviecataglogv3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.remotdata.MoviesRepository;

import java.util.ArrayList;

public class MoviesViewModel extends ViewModel {
    private LiveData<ArrayList<Movies>> moviesLiveData;
    private MoviesRepository moviesRepository;

    public MoviesViewModel() {
        moviesRepository = new MoviesRepository();

    }

    public void setMovies(String category, String lang, String apiKey) {
        moviesLiveData = moviesRepository.requestListMovie(category, lang, apiKey );
    }

    public LiveData<ArrayList<Movies>> getMovies() {
        return moviesLiveData;
    }

    public LiveData<String> getError() {
        return moviesRepository.errorRespon();
    }
}
