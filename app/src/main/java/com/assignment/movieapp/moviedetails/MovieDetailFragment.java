package com.assignment.movieapp.moviedetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.movieapp.R;
import com.assignment.movieapp.network.response.Result;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static String movieItemStr = null;
    private Result movieItem = null;
    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(String movieItemStr) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        MovieDetailFragment.movieItemStr = movieItemStr;
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

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        if(null != movieItemStr){
            Toast.makeText(getActivity(), movieItemStr, Toast.LENGTH_SHORT).show();
            Gson gson = new Gson();
            movieItem = gson.fromJson(movieItemStr , Result.class);

            TextView tvMovieTitle = (TextView) view.findViewById(R.id.tv_movie_title);
            TextView tvMovieOverview = (TextView) view.findViewById(R.id.tv_movie_overview);
            RatingBar rbMovieRate = (RatingBar) view.findViewById(R.id.rb_movie_rate);

            tvMovieTitle.setText(movieItem.getTitle());
            tvMovieOverview.setText(movieItem.getOverview());
            rbMovieRate.setRating(movieItem.getPopularity());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }
}
