package com.assignment.movieapp.moviedetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.assignment.movieapp.R;
import com.assignment.movieapp.network.response.Result;
import com.assignment.movieapp.util.Constants;
import com.google.gson.Gson;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_container_two_pane);

        String movieItemStr = getIntent().getStringExtra(Constants.MOVIE_ITEM);
        Gson gson = new Gson();
        Result movieItem = gson.fromJson(movieItemStr , Result.class);
        Toolbar appBar = (Toolbar) findViewById(R.id.app_bar);
        TextView tvAppBarTitle = (TextView) appBar.findViewById(R.id.tv_app_bar_title);
        tvAppBarTitle.setText(movieItem.getTitle()+"");
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        appBar.findViewById(R.id.iv_information).setVisibility(View.GONE);

        if(null != movieItemStr){
            MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(movieItemStr);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frag_container_two, movieDetailFragment, getString(R.string.frag_tag_text_movie_details))
                    .commit();

            MoviePosterListFragment movieImageListFragment = MoviePosterListFragment.newInstance(movieItem.getId());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frag_container_one, movieImageListFragment, getString(R.string.frag_tag_text_movie_image_list))
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
