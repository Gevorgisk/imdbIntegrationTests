package com.imdb.uitest.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.Selenium;

/**
 * This is base class for all test classes.
 * 
 * @author Gevorg Iskandaryan
 * 
 */
@Ignore
public class BaseTest {

	private TestUtilities testUtils = new TestUtilities();

	public WebDriver getDriver() {
		return testUtils.getDriver();
	}
	public Selenium getSelenium() {
		return testUtils.getSelenium();
	}

	public TestUtilities getTestUtils() {
		return testUtils;
	}

	@Before
	public void setUp() throws Exception {
		testUtils.setUp();
	}

	@After
	public void tearDown() throws Exception {
		testUtils.stop();
	}
}
