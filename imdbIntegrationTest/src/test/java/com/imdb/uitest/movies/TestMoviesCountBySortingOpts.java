package com.imdb.uitest.movies;
//this test is designed to make sure more then 1 Movie is displayed for all sorting types
import static com.imdb.uitest.util.Constants.TEST_TIMEOUT;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.imdb.uitest.base.BaseTest;
import com.imdb.uitest.base.TestUtilities;

public class TestMoviesCountBySortingOpts extends BaseTest {
	
	private static final String IMDB_RATING = "IMDb Rating";
	private static final String RELEASE_DATE = "Release Date";
	private static final String NUMBER_OF_RATINGS = "Number of Ratings";
	private static final String YOUR_RATING = "Your Rating"; 
	private static final String RANKING = "Ranking"; 
    int thresholdVal = 1;
    
    
	@Test(timeout = TEST_TIMEOUT)
	public void verifMoviesCount() throws Exception {
		

//		validate movies count by default sorting
		assertTrue(getTestUtils().getMoviesCountFromText() >= thresholdVal);
		assertTrue(getTestUtils().getMoviesCount() >= thresholdVal);
		
//		validate movies count for different sorting options
		getTestUtils().selectSortingMethod(IMDB_RATING);
		assertTrue(getTestUtils().getMoviesCountFromText() >= thresholdVal);
		assertTrue(getTestUtils().getMoviesCount() >= thresholdVal);
		
		getTestUtils().selectSortingMethod(RELEASE_DATE);
		assertTrue(getTestUtils().getMoviesCountFromText() >= thresholdVal);
		assertTrue(getTestUtils().getMoviesCount() >= thresholdVal);
		
		getTestUtils().selectSortingMethod(NUMBER_OF_RATINGS);
		assertTrue(getTestUtils().getMoviesCountFromText() >= thresholdVal);
		assertTrue(getTestUtils().getMoviesCount() >= thresholdVal);
		
		getTestUtils().selectSortingMethod(YOUR_RATING);
		assertTrue(getTestUtils().getMoviesCountFromText() >= thresholdVal);
		assertTrue(getTestUtils().getMoviesCount() >= thresholdVal);
		
//		test the initial sorting type
		getTestUtils().selectSortingMethod(RANKING);
		assertTrue(getTestUtils().getMoviesCountFromText() >= thresholdVal);
		assertTrue(getTestUtils().getMoviesCount() >= thresholdVal);
		

		System.out.println("FINISH");
	
	}
	

}
