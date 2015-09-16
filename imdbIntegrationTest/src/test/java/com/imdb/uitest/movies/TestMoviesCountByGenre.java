package com.imdb.uitest.movies;
// this test is designed to make sure more then 1 Movie is displayed for Western genre

import static com.imdb.uitest.util.Constants.TEST_TIMEOUT;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.imdb.uitest.base.BaseTest;

public class TestMoviesCountByGenre extends BaseTest {

	@Test(timeout = TEST_TIMEOUT)
	public void verifMoviesCountByGenre() throws Exception {

//		Select Western genre
		getTestUtils().selectMovieGenre("Western");
		
//		fail if Count of movies is less than 1 - i.e. no movie has been retrieved
		assertTrue(getTestUtils().getMoviesCountByGenre() >= 1);
		
		System.out.println("FINISH");

	}

}
