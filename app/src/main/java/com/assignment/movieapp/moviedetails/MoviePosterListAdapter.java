package com.assignment.movieapp.moviedetails;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.assignment.movieapp.R;
import com.assignment.movieapp.network.response.Poster;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rupali.Bhangale on 9/20/2017.
 */

public class MoviePosterListAdapter extends PagerAdapter {

    private ArrayList<Poster> images;
    private LayoutInflater inflater;
    private Context context;

    public MoviePosterListAdapter(Context context, ArrayList<Poster> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView imageView = (ImageView) myImageLayout
                .findViewById(R.id.image);
        Picasso.with(this.context)
                .load(images.get(position).getFilePath())
                .error(R.drawable.ic_image)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);

        //myImage.setImageResource(images.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}