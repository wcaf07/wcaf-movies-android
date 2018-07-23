package com.arctouch.codechallenge;

import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wanderleyfilho on 7/23/18.
 */

public class UpcomingMoviesResponseUnitTest {

    @Test
    public void isEqualsTest() {
        Genre genre = new Genre();
        genre.id = 2;
        genre.name = "comedy";

        Movie movie1 = new Movie();
        movie1.overview = "overview";
        movie1.title = "title";
        movie1.releaseDate = "2018-10-10";
        movie1.id = 3;
        movie1.genreIds = Arrays.asList(2,3,4);
        movie1.posterPath = "posterPath.jpg";
        movie1.backdropPath = "backdrop.jpg";
        movie1.genres = Arrays.asList(genre);

        Movie movie2 = new Movie();
        movie2.overview = "overview";
        movie2.title = "title";
        movie2.releaseDate = "2018-10-10";
        movie2.id = 3;
        movie2.genreIds = Arrays.asList(2,3,4);
        movie2.posterPath = "posterPath.jpg";
        movie2.backdropPath = "backdrop.jpg";
        movie2.genres = Arrays.asList(genre);

        UpcomingMoviesResponse upcomingMoviesResponse = new UpcomingMoviesResponse();
        upcomingMoviesResponse.results = Arrays.asList(movie1,movie2);
        upcomingMoviesResponse.page = 1;
        upcomingMoviesResponse.totalPages = 1;
        upcomingMoviesResponse.totalResults = 2;

        UpcomingMoviesResponse upcomingMoviesResponse2 = new UpcomingMoviesResponse();
        upcomingMoviesResponse2.results = Arrays.asList(movie1,movie2);
        upcomingMoviesResponse2.page = 1;
        upcomingMoviesResponse2.totalPages = 1;
        upcomingMoviesResponse2.totalResults = 2;

        Assert.assertEquals(upcomingMoviesResponse,upcomingMoviesResponse2);
    }

    @Test
    public void toStringTest() {
        Genre genre = new Genre();
        genre.id = 2;
        genre.name = "comedy";

        Movie movie1 = new Movie();
        movie1.overview = "overview";
        movie1.title = "title";
        movie1.releaseDate = "2018-10-10";
        movie1.id = 3;
        movie1.genreIds = Arrays.asList(2,3,4);
        movie1.posterPath = "posterPath.jpg";
        movie1.backdropPath = "backdrop.jpg";
        movie1.genres = Arrays.asList(genre);

        Movie movie2 = new Movie();
        movie2.overview = "overview";
        movie2.title = "title";
        movie2.releaseDate = "2018-10-10";
        movie2.id = 3;
        movie2.genreIds = Arrays.asList(2,3,4);
        movie2.posterPath = "posterPath.jpg";
        movie2.backdropPath = "backdrop.jpg";
        movie2.genres = Arrays.asList(genre);

        UpcomingMoviesResponse upcomingMoviesResponse = new UpcomingMoviesResponse();
        upcomingMoviesResponse.results = Arrays.asList(movie1,movie2);
        upcomingMoviesResponse.page = 1;
        upcomingMoviesResponse.totalPages = 1;
        upcomingMoviesResponse.totalResults = 2;

        Assert.assertEquals(upcomingMoviesResponse.toString(), "UpcomingMoviesResponse{page=1, results=[Movie{id=3, title='title', overview='overview', genres=[comedy], genreIds=[2, 3, 4], posterPath='posterPath.jpg', backdropPath='backdrop.jpg', releaseDate='2018-10-10'}, Movie{id=3, title='title', overview='overview', genres=[comedy], genreIds=[2, 3, 4], posterPath='posterPath.jpg', backdropPath='backdrop.jpg', releaseDate='2018-10-10'}], totalPages=1, totalResults=2}");
    }
}
