package com.assignment.movieapp.network;

import com.assignment.movieapp.network.response.MovieListResponse;
import com.assignment.movieapp.network.response.MoviePosterResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rupali.bhangale on 9/14/2017.
 */

public interface APIInteface {

    @GET("upcoming")
    Observable<MovieListResponse> getUpcomingMovieList();

    @GET("{movieId}/images")
    Observable<MoviePosterResponse> getMoviePosterList(@Path("movieId") int movieId);
}
