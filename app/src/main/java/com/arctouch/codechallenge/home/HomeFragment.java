package com.arctouch.codechallenge.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.controller.HomeController;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.details.DetailsFragment;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.EndlessRecyclerViewScrollListener;

import java.util.List;

/**
 * Created by wanderleyfilho on 7/22/18.
 */

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private HomeController homeController;
    private long currentPage = 1L;
    private List<Movie> movieList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_fragment, container, false);

        this.recyclerView = view.findViewById(R.id.recyclerView);
        this.progressBar = view.findViewById(R.id.progressBar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                currentPage += 1L;
                getNextMovies();
            }
        });

        homeController = new HomeController();
        homeController.setGenres().subscribe(response -> {
            Cache.setGenres(response.genres);
            loadMovies();
        });

        return view;
    }

    public void loadMovies() {
        homeController.loadUpcomingMoviesByPage(this.currentPage).subscribe(response -> {
            movieList = homeController.addGenreToMovies(response.results);

            recyclerView.setAdapter(new HomeAdapter(movieList, new HomeAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(Movie movie) {
                    DetailsFragment detailsFragment = new DetailsFragment();
                    detailsFragment.movie = movie;
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,detailsFragment).addToBackStack(null).commit();
                }
            }));
            progressBar.setVisibility(View.GONE);
        });
    }

    public void getNextMovies() {
        homeController.loadUpcomingMoviesByPage(this.currentPage).subscribe(response -> {
            movieList.addAll(homeController.addGenreToMovies(response.results));
            recyclerView.getAdapter().notifyDataSetChanged();
        });
    }
}
