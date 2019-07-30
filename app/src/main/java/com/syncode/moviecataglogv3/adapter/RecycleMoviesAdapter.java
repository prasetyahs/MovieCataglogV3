package com.syncode.moviecataglogv3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syncode.moviecataglogv3.DetailActivity;
import com.syncode.moviecataglogv3.R;
import com.syncode.moviecataglogv3.model.Movies;

import java.util.List;

public class RecycleMoviesAdapter extends RecyclerView.Adapter<RecycleMoviesAdapter.ViewHolder> {

    private List<Movies> listMovies;

    private final static String BASE_URL_POSTER = "https://image.tmdb.org/t/p/w342";

    private Context context;
    private String type;

    public RecycleMoviesAdapter(List<Movies> listMovies, Context context, String type) {
        this.listMovies = listMovies;
        this.context = context;
        this.type = type;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_rows, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movies moviesModel = listMovies.get(position);
        holder.txtDesc.setText(moviesModel.getOverView());
        if (moviesModel.getTitle() != null) {
            holder.txtTitle.setText(moviesModel.getTitle());
        } else {
            holder.txtTitle.setText(moviesModel.getTitleOriginal());
        }
        if (moviesModel.getReleaseDate() != null) {
            holder.txtDate.setText(moviesModel.getReleaseDate());
        } else {
            holder.txtDate.setText(moviesModel.getDateTv());
        }
        Glide.with(context).load(BASE_URL_POSTER + moviesModel.getPosterPath()).into(holder.imgMovie);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetail = new Intent(context, DetailActivity.class);
                goToDetail.putExtra("type", type);
                goToDetail.putExtra("movies", moviesModel);
                context.startActivity(goToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView txtTitle;
        TextView txtDate;
        TextView txtDesc;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_movie);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtDesc = itemView.findViewById(R.id.txt_desc);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
