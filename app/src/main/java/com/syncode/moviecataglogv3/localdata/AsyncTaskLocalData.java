package com.syncode.moviecataglogv3.localdata;

import com.syncode.moviecataglogv3.model.Movies;

public interface AsyncTaskLocalData {

    void onPostExecute(Movies[] object);
}
