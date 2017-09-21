package com.assignment.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.movieapp.MovieListFragment.OnMovieListFragmentInteractionListener;
import com.assignment.movieapp.network.response.MovieListResponse;
import com.assignment.movieapp.network.response.Result;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnMovieListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private final List<Result> upcomingMovieList;
    private final OnMovieListFragmentInteractionListener onMovieListFragmentInteractionListener;
    private final Context context;

    public MovieRecyclerViewAdapter(Context context, List<Result> upcomingMovieList, OnMovieListFragmentInteractionListener onMovieListFragmentInteractionListener) {
        this.context = context;
        this.upcomingMovieList = upcomingMovieList;
        this.onMovieListFragmentInteractionListener = onMovieListFragmentInteractionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvMovieName.setText(upcomingMovieList.get(position).getTitle().toString().trim());
        holder.tvMovieReleaseDate.setText(upcomingMovieList.get(position).getReleaseDate().toString().trim());
        if(upcomingMovieList.get(position).isAdult())
            holder.tvMovieType.setText("A");
        else
            holder.tvMovieType.setText("U/A");

        Picasso.with(this.context)
                .load(upcomingMovieList.get(position).getPosterPath())
                .error(R.drawable.ic_image)
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.ivMovieThumbnail);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onMovieListFragmentInteractionListener) {
                    Gson gson = new Gson();
                    String movieItemStr = gson.toJson(upcomingMovieList.get(position));
                    onMovieListFragmentInteractionListener.showMovieDetails(movieItemStr);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return upcomingMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private final TextView tvMovieName;
        private final TextView tvMovieReleaseDate;
        private final TextView tvMovieType;
        private final ImageView ivMovieThumbnail;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivMovieThumbnail = (ImageView) view.findViewById(R.id.iv_movie_thumbnail);
            tvMovieName = (TextView) view.findViewById(R.id.tv_movie_name);
            tvMovieReleaseDate = (TextView) view.findViewById(R.id.tv_movie_release_date);
            tvMovieType = (TextView) view.findViewById(R.id.tv_movie_type);
        }
    }
}
