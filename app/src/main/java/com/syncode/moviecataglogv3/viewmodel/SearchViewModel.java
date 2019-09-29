package com.syncode.moviecataglogv3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.remotdata.MoviesRepository;

import java.util.ArrayList;

public class SearchViewModel extends ViewModel {

    private LiveData<ArrayList<Movies>> liveDataMovie;
    private MoviesRepository moviesRepository;

    public SearchViewModel() {
        moviesRepository = new MoviesRepository();
    }

    public void setMovies(String category, String lang, String apiKey, String query) {
        liveDataMovie = moviesRepository.searchMovies(category, lang, apiKey, query);

    }

    public LiveData<ArrayList<Movies>> getMovies() {
        return liveDataMovie;
    }

    public LiveData<String> getError() {
        return moviesRepository.errorRespon();
    }
}
