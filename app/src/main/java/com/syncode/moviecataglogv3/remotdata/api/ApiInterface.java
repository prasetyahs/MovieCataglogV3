package com.syncode.moviecataglogv3.remotdata.api;

import com.syncode.moviecataglogv3.remotdata.response.MoviesResponse;
import com.syncode.moviecataglogv3.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/discover/{category}")
    Call<MoviesResponse> getMovies(@Path("category") String category, @Query("api_key") String api_key, @Query("language") String lang);

    @GET("3/{category}/{id}")
    Call<Movies> getSingleMovie(@Path("category")String category,@Path("id") int id, @Query("api_key") String api_key, @Query("language") String lang);

}
