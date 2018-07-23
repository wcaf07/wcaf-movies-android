package com.arctouch.codechallenge.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by wanderleyfilho on 7/22/18.
 */

public class DetailsFragment extends Fragment {

    public Movie movie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.details_fragment, container, false);
        final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();
        setHasOptionsMenu(true);

        ImageView posterImage = view.findViewById(R.id.imagePosterDetails);
        TextView genreTextView = view.findViewById(R.id.textGenre);
        TextView overviewTextView = view.findViewById(R.id.textOverview);
        TextView releaseTextView = view.findViewById(R.id.textRelease);

        if (TextUtils.isEmpty(movie.backdropPath) == false) {
            Glide.with(posterImage)
                    .load(movieImageUrlBuilder.buildBackdropUrl(movie.backdropPath))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(posterImage);
        }
        genreTextView.setText(TextUtils.join(", ", movie.genres));
        overviewTextView.setText(movie.overview);
        releaseTextView.setText("Release date: "+movie.releaseDate);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.search);
        item.setVisible(false);
    }
}
