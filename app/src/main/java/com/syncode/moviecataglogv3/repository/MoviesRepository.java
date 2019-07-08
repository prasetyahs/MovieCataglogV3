package com.syncode.moviecataglogv3.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.syncode.moviecataglogv3.api.ApiClient;
import com.syncode.moviecataglogv3.api.ApiInterface;
import com.syncode.moviecataglogv3.api.response.MoviesResponse;
import com.syncode.moviecataglogv3.model.Movies;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private ApiInterface apiInterface;

    private String message;
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movies>> data = new MutableLiveData<>();
    private MutableLiveData<Movies> movie = new MutableLiveData<>();

    public MoviesRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public LiveData<ArrayList<Movies>> requestListMovie(String category, String lang, String apiKey) {
        final Call<MoviesResponse> moviesResponseCall = apiInterface.getMovies(category, apiKey, lang);
        moviesResponseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response.body() != null) {
                    data.postValue(response.body().getMoviesList());
                } else {
                    message = "Data Not Found";
                    errorMessage.setValue(message);
                }

            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                message = "No Internet";
                errorMessage.setValue(message);
            }
        });
        return data;
    }


    public LiveData<String> errorRespon() {
        return errorMessage;
    }

    public LiveData<Movies> requestSingleMovie(int id, String lang, String apiKey, String category) {
        Call<Movies> moviesCall = apiInterface.getSingleMovie(category, id, apiKey, lang);
        moviesCall.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                Movies responseMovie = response.body();
                if (responseMovie != null) {
                    Movies itemsMovie = new Movies(
                            responseMovie.getId(),
                            responseMovie.getVoteCount(),
                            responseMovie.getOverView(),
                            responseMovie.getVoteAverage(),
                            responseMovie.getTitle(),
                            responseMovie.getPopularity(),
                            responseMovie.getPosterPath(),
                            responseMovie.getLanguage(),
                            responseMovie.getReleaseDate(),
                            responseMovie.getBackDropPath(),
                            responseMovie.getBudget(), responseMovie.getRuntime(), responseMovie.getCredits(), responseMovie.getTitleOriginal(), responseMovie.getFirstDate());
                    movie.postValue(itemsMovie);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {

            }
        });
        return movie;
    }
}
