package com.arctouch.codechallenge;

import com.arctouch.codechallenge.controller.HomeController;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Cache.class)
public class HomeControllerUnitTest {

    @Mock
    HomeController homeController;

    @Before
    public void prepareForTest() {
        homeController = new HomeController();
    }

    @Test
    public void addMovies() {
        Genre genreTest = new Genre();
        genreTest.id = 12;
        genreTest.name = "Adventure";
        List<Genre> genreList = Arrays.asList(genreTest);

        PowerMockito.mockStatic(Cache.class);
        Mockito.when(Cache.getGenres()).thenReturn(genreList);

        Movie movie = new Movie();
        movie.genreIds = Arrays.asList(12);
        List<Movie> movieList =  homeController.addGenreToMovies(Arrays.asList(movie));

        Movie movieTest = new Movie();
        movieTest.genres = genreList;
        movieTest.genreIds = Arrays.asList(12);
        assertEquals(movieList.equals(Arrays.asList(movieTest)), true);
    }

}