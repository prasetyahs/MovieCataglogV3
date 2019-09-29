package com.syncode.moviecataglogv3.localdata;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.syncode.moviecataglogv3.model.Movies;

@Dao
public interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movies movies);

    @Query("Select * from tb_movie Where id LIKE :search")
    Movies[] checkMovies(int search);

    @Delete
    void deleteMovie(Movies movies);


    @Query("SELECT * From tb_movie Where type LIKE:type")
    Movies[] readWithType(String type);


    @Query("SELECT * From tb_movie Where type LIKE:type")
    Cursor readAll(String type);


}
