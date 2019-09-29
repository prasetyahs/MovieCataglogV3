package com.syncode.moviecataglogv3.remotdata.api;

import com.syncode.moviecataglogv3.remotdata.response.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/discover/{category}")
    Call<MoviesResponse> getMovies(@Path("category") String category, @Query("api_key") String api_key, @Query("language") String lang);


    @GET("3/search/{category}/")
    Call<MoviesResponse> searchMovies(@Path("category") String category, @Query("api_key") String apiKey, @Query("language") String lang, @Query("query") String query);

    @GET("3/discover/movie/")
    Call<MoviesResponse> releaseMovies(@Query("api_key") String apiKey, @Query("primary_release_date.gte") String primaryDateGte, @Query("primary_release_date.lte") String primaryDateLte);

}
