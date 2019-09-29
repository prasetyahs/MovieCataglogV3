package com.syncode.moviecataglogv3.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.syncode.moviecataglogv3.remotdata.MoviesRepository;
import com.syncode.moviecataglogv3.remotdata.api.Constanta;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReleaseAlarm extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        @SuppressLint("SimpleDateFormat") String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        MoviesRepository moviesRepository = new MoviesRepository();
        moviesRepository.requestReleaseMovie(Constanta.API_KEY, strDate, strDate, context);
    }
}
