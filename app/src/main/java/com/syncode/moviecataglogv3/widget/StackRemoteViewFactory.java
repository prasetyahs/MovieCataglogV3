package com.syncode.moviecataglogv3.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.syncode.moviecataglogv3.R;
import com.syncode.moviecataglogv3.localdata.MovieDatabase;
import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.remotdata.api.Constanta;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StackRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private ArrayList<Bitmap> widgetBitmap = new ArrayList<>();

    StackRemoteViewFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        MovieDatabase dbMovie = Room.databaseBuilder(context, MovieDatabase.class, "MovieDb").build();
        Movies[] movies = dbMovie.movieDAO().readWithType("Movie");
        widgetBitmap.clear();
        for (Movies m : movies) {
            try {
                Bitmap bitmap = Glide.with(context).asBitmap().load(Constanta.BASE_URL_POSTER + m.getBackDropPath()).submit().get();
                widgetBitmap.add(bitmap);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return widgetBitmap.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_items);
        remoteViews.setImageViewBitmap(R.id.imageView, widgetBitmap.get(i));
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


}
