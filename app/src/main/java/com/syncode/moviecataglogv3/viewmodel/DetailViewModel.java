package com.syncode.moviecataglogv3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.remotdata.MoviesRepository;

public class DetailViewModel extends ViewModel {

    private LiveData<Movies> moviesLiveData;
    private MoviesRepository moviesRepository;

    public DetailViewModel() {
        moviesRepository = new MoviesRepository();
    }

    public void setMovie(int id, String lang, String apiKey, String category) {
        moviesLiveData = moviesRepository.requestSingleMovie(id, lang, apiKey, category);
    }

    public LiveData<Movies> getMovie() {
        return moviesLiveData;
    }


}
