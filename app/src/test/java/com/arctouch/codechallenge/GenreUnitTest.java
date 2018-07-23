package com.arctouch.codechallenge;

import com.arctouch.codechallenge.model.Genre;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wanderleyfilho on 7/23/18.
 */

public class GenreUnitTest {

    @Test
    public void isEqualsTest() {
        Genre genre = new Genre();
        genre.id = 2;
        genre.name = "comedy";

        Genre genre2 = new Genre();
        genre2.id = 2;
        genre2.name = "comedy";

        Assert.assertEquals(genre,genre2);
    }

    @Test
    public void toStringTest() {
        Genre genre = new Genre();
        genre.id = 2;
        genre.name = "comedy";

        Assert.assertEquals(genre.toString(), "comedy");
    }
}
