package com.syncode.moviecataglogv3.localdata;

import android.os.AsyncTask;

import com.syncode.moviecataglogv3.model.Movies;

import java.lang.ref.WeakReference;

public class AsyncOperation extends AsyncTask<String[], Integer, Movies[]> {
    private WeakReference<AsyncTaskLocalData> listenerCallback;
    private MovieDatabase movieDatabase;
    private Movies movies;

    public AsyncOperation(AsyncTaskLocalData listenerCallback, MovieDatabase movieDatabase, Movies movies) {
        this.listenerCallback = new WeakReference<>(listenerCallback);
        this.movieDatabase = movieDatabase;
        this.movies = movies;
    }


    @Override
    protected void onPostExecute(Movies[] movies) {
        super.onPostExecute(movies);
        AsyncTaskLocalData listenCallback = this.listenerCallback.get();
        if (listenCallback != null) {
            listenCallback.onPostExecute(movies);
        }
    }


    @Override
    protected Movies[] doInBackground(String[]... params) {
        String command = params[0][0];
        String id = params[0][1];
        switch (command) {
            case "insert":
                movieDatabase.movieDAO().insertMovie(movies);
                return null;
            case "delete":
                movieDatabase.movieDAO().deleteMovie(movies);
                return null;
            case "check":
                return movieDatabase.movieDAO().checkMovies(Integer.valueOf(id));
            case "read":
                String type = params[0][2];
                return movieDatabase.movieDAO().readWithType(type);
            default:
                return null;
        }
    }
}
