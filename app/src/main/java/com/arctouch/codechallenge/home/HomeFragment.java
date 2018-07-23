package com.arctouch.codechallenge.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

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
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private boolean isSearching = false;
    private String querySearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_fragment, container, false);
        setHasOptionsMenu(true);

        this.recyclerView = view.findViewById(R.id.recyclerView);
        this.progressBar = view.findViewById(R.id.progressBar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                currentPage += 1L;
                getNextMovies();
            }
        };
        this.recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);

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
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,detailsFragment).addToBackStack(null).commit();
                }
            }));
            progressBar.setVisibility(View.GONE);
        });
    }

    public void getNextMovies() {
        if (isSearching) {
            homeController.searchMovies(this.currentPage, this.querySearch).subscribe(response -> {
                movieList.addAll(homeController.addGenreToMovies(response.results));
                recyclerView.getAdapter().notifyDataSetChanged();
            });
        } else {
            homeController.loadUpcomingMoviesByPage(this.currentPage).subscribe(response -> {
                movieList.addAll(homeController.addGenreToMovies(response.results));
                recyclerView.getAdapter().notifyDataSetChanged();
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        MenuItem searchItem = menu.findItem(R.id.search);

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                isSearching = false;
                resetIndexes();

                loadMovies();
                return true;
            }
        });

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                querySearch = query;
                searchMovies();
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
    }

    public void searchMovies() {
        this.isSearching = true;
        this.resetIndexes();
        this.progressBar.setVisibility(View.VISIBLE);

        homeController.searchMovies(this.currentPage, this.querySearch).subscribe(response -> {
            movieList = homeController.addGenreToMovies(response.results);
            recyclerView.setAdapter(new HomeAdapter(movieList, new HomeAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(Movie movie) {
                    DetailsFragment detailsFragment = new DetailsFragment();
                    detailsFragment.movie = movie;
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,detailsFragment).addToBackStack(null).commit();
                }
            }));
            progressBar.setVisibility(View.GONE);
        });
    }

    public void resetIndexes() {
        this.currentPage = 1L;
        this.endlessRecyclerViewScrollListener.resetState();
    }
}
