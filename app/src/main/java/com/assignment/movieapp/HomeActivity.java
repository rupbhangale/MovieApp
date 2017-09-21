package com.assignment.movieapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.assignment.movieapp.information.InformationActivity;
import com.assignment.movieapp.moviedetails.MovieDetailsActivity;
import com.assignment.movieapp.util.Constants;

public class HomeActivity extends AppCompatActivity implements MovieListFragment.OnMovieListFragmentInteractionListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar appBar = (Toolbar) findViewById(R.id.app_bar);
        TextView tvAppBarTitle = (TextView) appBar.findViewById(R.id.tv_app_bar_title);
        tvAppBarTitle.setText(getString(R.string.tv_text_upcoming_movies));
        setSupportActionBar(appBar);
        ImageView ivInformation = (ImageView) appBar.findViewById(R.id.iv_information);
        ivInformation.setOnClickListener(this);

        MovieListFragment movieListFragment = MovieListFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.home_frag_container, movieListFragment, getString(R.string.frag_tag_text_upcoming_movies));
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.iv_information:
                Intent informationIntent = new Intent(HomeActivity.this, InformationActivity.class);
                startActivity(informationIntent);
                break;
        }
    }

    @Override
    public void showMovieDetails(String movieItemStr) {
        Intent movieDetailsIntent = new Intent(HomeActivity.this, MovieDetailsActivity.class);
        movieDetailsIntent.putExtra(Constants.MOVIE_ITEM, movieItemStr);
        startActivity(movieDetailsIntent);
    }
}
