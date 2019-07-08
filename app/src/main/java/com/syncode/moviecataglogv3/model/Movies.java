package com.syncode.moviecataglogv3.model;

import com.google.gson.annotations.SerializedName;

public class Movies {

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("overview")

    private String overView;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("original_name")
    private String title;

    @SerializedName("original_title")
    private String titleOriginal;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String language;


    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("backdrop_path")
    private String backDropPath;

    @SerializedName("budget")
    private int budget;

    @SerializedName("runtime")
    private double runtime;

    @SerializedName("credits")
    private String credits;

    @SerializedName("first_air_date")
    private String firstDate;

    public Movies(int id, int voteCount, String overView, float voteAverage, String title, String popularity, String posterPath, String language, String releaseDate, String backDropPath, int budget, double runtime, String credits, String titleOriginal, String firstDate) {
        this.id = id;
        this.voteCount = voteCount;
        this.overView = overView;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.language = language;
        this.releaseDate = releaseDate;
        this.backDropPath = backDropPath;
        this.budget = budget;
        this.runtime = runtime;
        this.credits = credits;
        this.titleOriginal = titleOriginal;
        this.firstDate = firstDate;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public String getTitleOriginal() {
        return titleOriginal;
    }

    public String getCredits() {
        return credits;
    }

    public double getRuntime() {
        return runtime;
    }

    public int getBudget() {
        return budget;
    }

    public int getId() {
        return id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public String getOverView() {
        return overView;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getLanguage() {
        return language;
    }
}
