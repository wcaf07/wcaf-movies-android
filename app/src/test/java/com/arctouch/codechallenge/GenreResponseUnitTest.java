package com.arctouch.codechallenge;

import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.GenreResponse;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanderleyfilho on 7/23/18.
 */

public class GenreResponseUnitTest {

    @Test
    public void isEqualsTest() {
        Genre genre = new Genre();
        genre.id = 1;
        genre.name = "Adventure";

        Genre genre2 = new Genre();
        genre2.id = 2;
        genre2.name = "Horror";

        List<Genre> genreList = Arrays.asList(genre,genre2);
        GenreResponse response = new GenreResponse();
        response.genres = genreList;

        GenreResponse response2 = new GenreResponse();
        response2.genres = genreList;

        Assert.assertEquals(response, response2);
    }

    @Test
    public void toStringTest() {
        Genre genre = new Genre();
        genre.id = 1;
        genre.name = "Adventure";

        Genre genre2 = new Genre();
        genre2.id = 2;
        genre2.name = "Horror";

        List<Genre> genreList = Arrays.asList(genre,genre2);
        GenreResponse response = new GenreResponse();
        response.genres = genreList;

        Assert.assertEquals(response.toString(), "GenreResponse{genres=[Adventure, Horror]}");
    }
}
