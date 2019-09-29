package com.syncode.moviecataglogv3.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.syncode.moviecataglogv3.localdata.MovieDatabase;

public class MovieProvider extends ContentProvider {


    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        final Context context = getContext();
        if (context == null) {
            return null;
        }
        MovieDatabase dbMovie = Room.databaseBuilder(this.getContext(), MovieDatabase.class, "MovieDb").build();
        Cursor cursor = dbMovie.movieDAO().readAll("Movie");
        cursor.setNotificationUri(context.getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
