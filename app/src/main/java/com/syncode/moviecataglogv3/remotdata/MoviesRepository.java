package com.syncode.moviecataglogv3.remotdata;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.reminder.Alarm;
import com.syncode.moviecataglogv3.remotdata.api.ApiClient;
import com.syncode.moviecataglogv3.remotdata.api.ApiInterface;
import com.syncode.moviecataglogv3.remotdata.response.MoviesResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private ApiInterface apiInterface;

    private String message;
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movies>> data = new MutableLiveData<>();
    private ArrayList<Movies> dataRelease = new ArrayList<>();

    public MoviesRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public LiveData<ArrayList<Movies>> searchMovies(String category, String lang, String apiKey, String query) {
        Call<MoviesResponse> responseSearch = apiInterface.searchMovies(category, apiKey, lang, query);
        responseSearch.enqueue(new Callback<MoviesResponse>() {
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

    public void requestReleaseMovie(String apiKey, String gte, String lte, Context context) {
        Call<MoviesResponse> moviesResponseCall = apiInterface.releaseMovies(apiKey, gte, lte);
        moviesResponseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                if (response.body() != null) {
                    for (Movies movies : response.body().getMoviesList()) {
                        Alarm.showNotification(movies.getTitleOriginal(), context, movies.getTitleOriginal()+" Release Today !", movies.getId(), String.valueOf(movies.getId()), String.valueOf(movies.getId()),5);
                    }

                } else {
                    message = "Not Release";
                    errorMessage.setValue(message);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                message = "No Internet";
                errorMessage.setValue(message);
            }
        });
    }
}
