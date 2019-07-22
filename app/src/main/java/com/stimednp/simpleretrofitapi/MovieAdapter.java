package com.stimednp.simpleretrofitapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.Movie;

/**
 * Created by rivaldy on 7/21/2019.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> moviesData = new ArrayList<>();
    private Context context;

    MovieAdapter(Context context) {
        this.context = context;
    }

    void setMoviesData(ArrayList<Movie> items) {
        moviesData.clear();
        moviesData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.bind(moviesData.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView backbg;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviesLayout = itemView.findViewById(R.id.movies_layout);
            movieTitle = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.subtitle);
            movieDescription = itemView.findViewById(R.id.description);
            rating = itemView.findViewById(R.id.rating);
            backbg = itemView.findViewById(R.id.backbg);
        }

        void bind(Movie movies) {
        movieTitle.setText(movies.getTitle());
        data.setText(movies.getReleaseDate());
        movieDescription.setText(movies.getOverview());
        rating.setText(movies.getVoteAverage().toString());
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movies.getBackdropPath()).resize(200, 250)
                .into(backbg);
        }
    }
}
