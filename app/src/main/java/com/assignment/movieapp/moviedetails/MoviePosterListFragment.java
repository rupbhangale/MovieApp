package com.assignment.movieapp.moviedetails;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.assignment.movieapp.R;
import com.assignment.movieapp.network.APIClient;
import com.assignment.movieapp.network.APIInteface;
import com.assignment.movieapp.network.response.MoviePosterResponse;
import com.assignment.movieapp.network.response.Poster;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MoviePosterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviePosterListFragment extends Fragment {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int movieId = 0;
    private ArrayList<Poster> moviePosterList = new ArrayList<Poster>();
    private CompositeDisposable movieListCompositeDisposable = null;
    private CircleIndicator indicator = null;

    public static MoviePosterListFragment newInstance(int movieId) {
        MoviePosterListFragment fragment = new MoviePosterListFragment();
        MoviePosterListFragment.movieId = movieId;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_image_list, container, false);
        movieListCompositeDisposable = new CompositeDisposable();
        mPager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        getMovieImageList();

        return view;
    }


    private void getMovieImageList() {
        APIInteface apiInteface = APIClient.getAPIInterface();
        movieListCompositeDisposable.add(apiInteface.getMoviePosterList(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }
    private void handleResponse(MoviePosterResponse moviePosterResponse) {
       // Toast.makeText(getActivity(), "Success ", Toast.LENGTH_SHORT).show();
        moviePosterList.addAll(moviePosterResponse.getPosters());
        init();
    }

    private void handleError(Throwable error) {
        Toast.makeText(getActivity(), "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
    private void init() {
        mPager.setAdapter(new MoviePosterListAdapter(getActivity(),moviePosterList));
       indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == moviePosterList.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
