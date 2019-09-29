package com.syncode.moviecataglogv3.localdata;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.syncode.moviecataglogv3.model.Movies;

@Database(entities = {Movies.class}, version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();

}
