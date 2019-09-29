package com.syncode.moviecataglogv3;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.syncode.moviecataglogv3.localdata.AsyncOperation;
import com.syncode.moviecataglogv3.localdata.AsyncTaskLocalData;
import com.syncode.moviecataglogv3.localdata.MovieDatabase;
import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.remotdata.api.Constanta;

public class DetailActivity extends AppCompatActivity implements AsyncTaskLocalData {

    private MovieDatabase dbMovie;
    private Movies movies;
    private MenuItem itemFavorite;
    private String[] params;
    private static final int LOADER_MENU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_detail);
        dbMovie = Room.databaseBuilder(this.getApplicationContext(), MovieDatabase.class, "MovieDb").build();
        ImageView imgMovie = findViewById(R.id.img_movie);
        TextView txtLang = findViewById(R.id.txt_lang);
        TextView txtDate = findViewById(R.id.txt_date);
        ProgressBar progress = findViewById(R.id.progress_circular);
        TextView txtPercent = findViewById(R.id.txt_percent);
        LinearLayout containerStar = findViewById(R.id.container_star);
        TextView txtOverview = findViewById(R.id.txt_overview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        movies = getIntent().getParcelableExtra("movies");
        String type = getIntent().getStringExtra("type");
        movies.setType(type);
        if (movies != null) {
            txtLang.setText(movies.getLanguage());
            float voteAverage = (movies.getVoteAverage() / 100) * 100 * 10;
            txtPercent.setText(String.valueOf((int) voteAverage).concat("%"));
            txtOverview.setText(movies.getOverView());
            if (movies.getReleaseDate() != null) {
                txtDate.setText(movies.getReleaseDate());
            } else {
                txtDate.setText(movies.getDateTv());
            }
            Glide.with(this).load(Constanta.BASE_URL_IMG + movies.getBackDropPath()).into(imgMovie);
            float starCount = (voteAverage / 100) * 5;
            if (containerStar.findViewWithTag("star") == null) {
                for (int i = 0; i < Math.floor(starCount); i++) {
                    ImageView star = new ImageView(DetailActivity.this);
                    star.setTag("star");
                    star.setImageResource(R.drawable.ic_star_orange_24dp);
                    containerStar.addView(star);
                }
            }
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(progress, "progress", (int) voteAverage);
            objectAnimator.setDuration(1000);
            objectAnimator.start();
            String contentTitle;
            if (movies.getTitle() != null) {
                contentTitle = movies.getTitle();
            } else {
                contentTitle = movies.getTitleOriginal();
            }
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(contentTitle);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_favorite) {
            if (itemFavorite.isChecked()) {
                itemFavorite.setIcon(getResources().getDrawable(R.drawable.ic_favorite_gray_24dp));
                itemFavorite.setChecked(false);
                params = new String[]{"delete", String.valueOf(movies.getId())};
                new AsyncOperation(this, dbMovie, movies).execute(params);
            } else {
                params = new String[]{"insert", ""};
                new AsyncOperation(this, dbMovie, movies).execute(params);
                itemFavorite.setIcon(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
                itemFavorite.setChecked(true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        params = new String[]{"check", String.valueOf(movies.getId())};
        new AsyncOperation(this, dbMovie, movies).execute(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.detail_options_menu, menu);
        itemFavorite = menu.findItem(R.id.add_favorite);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    @Override
    public void onPostExecute(Movies[] movies) {
        if (movies != null) {
            if (movies.length > 0) {
                Drawable icon = getResources().getDrawable(R.drawable.ic_favorite_red_24dp);
                if (icon != null) {
                    itemFavorite.setIcon(icon);
                    itemFavorite.setChecked(true);
                }

            }
        }
    }
}


