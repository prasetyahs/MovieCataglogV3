package com.syncode.moviecataglogv3;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.syncode.moviecataglogv3.api.Constanta;
import com.syncode.moviecataglogv3.model.Movies;
import com.syncode.moviecataglogv3.repository.SharedPreference;
import com.syncode.moviecataglogv3.viewmodel.DetailViewModel;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout containerStar;
    ProgressBar progress;
    TextView txtLang, txtDate, txtPercent, txtOverview, txtBudget;
    ImageView imgMovie;
    SharedPreference sharedPreference;
    ShimmerFrameLayout shimmerFrameLayout;
    NestedScrollView nestedScrollView;
    DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_detail);
        imgMovie = findViewById(R.id.img_movie);
        txtLang = findViewById(R.id.txt_lang);
        txtDate = findViewById(R.id.txt_date);
        progress = findViewById(R.id.progress_circular);
        txtPercent = findViewById(R.id.txt_percent);
        containerStar = findViewById(R.id.container_star);
        txtOverview = findViewById(R.id.txt_overview);
        txtBudget = findViewById(R.id.txt_budget);
        shimmerFrameLayout = findViewById(R.id.shimmerContainer);
        nestedScrollView = findViewById(R.id.nestedView);
        shimmerFrameLayout.startShimmerAnimation();
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        toolbar = findViewById(R.id.toolbar);
        Intent getIntent = getIntent();
        sharedPreference = new SharedPreference(this);
        int id = getIntent.getIntExtra("id", 0);
        String category = getIntent.getStringExtra("category");
        detailViewModel.setMovie(id, sharedPreference.getReferences(), Constanta.API_KEY, category);
        detailViewModel.getMovie().observe(this, getMovie);
    }



    private Observer<Movies> getMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            setSupportActionBar(toolbar);
            if (movies.getBackDropPath() != null) {
                shimmerFrameLayout.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmerAnimation();
                nestedScrollView.setVisibility(View.VISIBLE);
            }

            String contentTitle;
            if (movies.getTitle() != null) {
                contentTitle = movies.getTitle();
            } else {
                contentTitle = movies.getTitleOriginal();
            }

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(contentTitle);
            }
            Glide.with(DetailActivity.this).load(Constanta.BASE_URL_IMG + movies.getBackDropPath()).into(imgMovie);
            txtLang.setCompoundDrawablePadding(20);
            txtLang.setText(movies.getLanguage());
            txtOverview.setText(movies.getOverView());
            txtLang.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_language_black_16dp, 0, 0, 0);
            if (movies.getReleaseDate() != null) {
                txtDate.setText(movies.getReleaseDate());
            } else {
                txtDate.setText(movies.getFirstDate());
            }
            txtBudget.setText(NumberFormat.getCurrencyInstance(Locale.US).format(movies.getBudget()));
            float voteAverage = (movies.getVoteAverage() / 100) * 100 * 10;
            txtPercent.setText(String.valueOf((int) voteAverage).concat("%"));
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
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}


