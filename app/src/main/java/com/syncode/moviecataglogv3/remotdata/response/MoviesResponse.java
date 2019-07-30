package com.syncode.moviecataglogv3.remotdata.response;

import com.google.gson.annotations.SerializedName;
import com.syncode.moviecataglogv3.model.Movies;

import java.util.ArrayList;

public class MoviesResponse {

    @SerializedName("results")
    private ArrayList<Movies> moviesList;


    public MoviesResponse(ArrayList<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    public ArrayList<Movies> getMoviesList() {
        return moviesList;
    }
}
