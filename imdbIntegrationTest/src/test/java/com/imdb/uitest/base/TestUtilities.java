package com.imdb.uitest.base;

import static com.imdb.uitest.util.Constants.BROWSER_HEIGHT_PERCENT;
import static com.imdb.uitest.util.Constants.BROWSER_WIDTH_PERCENT;

import java.awt.Toolkit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.imdb.uitest.util.Constants;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
/**
 * This is Utilities class for the methods for common actions.
 * 
 * @author Gevorg Iskandaryan
 * 
 */
public class TestUtilities {
	private static final Point BROWSER_POSITION = new Point(0, 0);
	private Dimension browserDimension;

	private static final String IMDB_WEB_INDEX_HTML = "chart/top";
	public static final String baseUrl = "http://imdb.com/";

	private Selenium selenium;
	private WebDriver driver;

	public TestUtilities() {
	}

	public TestUtilities(Selenium selenium, WebDriver driver) {
		this.selenium = selenium;
		this.driver = driver;
	}

	public Selenium getSelenium() {
		return selenium;
	}

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * setUp method to launch the browser with selected application from
	 * test.properties file
	 * 
	 * @throws InterruptedException
	 */
	public void setUp() throws InterruptedException {
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();

		int x = new Double(screenSize.getWidth() * BROWSER_WIDTH_PERCENT)
				.intValue();
		int y = new Double(screenSize.getHeight() * BROWSER_HEIGHT_PERCENT)
				.intValue();
		browserDimension = new Dimension(x, y);

		FirefoxProfile profile = new FirefoxProfile();
		profile.setEnableNativeEvents(false);
		driver = new FirefoxDriver(profile);
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		selenium.waitForPageToLoad(Constants.LOAD_DELAY);
		selenium.open(IMDB_WEB_INDEX_HTML);
		Thread.sleep(2000);

		driver.manage().window().setPosition(BROWSER_POSITION);
		driver.manage().window().setSize(browserDimension);
	}

	/**
 * The method is designed to stop the test 
 */
	public void stop() {
		selenium.stop();
	}

	/**
	 * This method is designed to change sorting method of the Movies
	 * 
	 * @param sortingType
	 * @throws InterruptedException
	 */
	public void selectSortingMethod(String sortingType)
			throws InterruptedException {

		WebElement dropDown = getDriver().findElement(
				By.cssSelector("select[class=lister-sort-by]"));

		List<WebElement> sortingOptions = dropDown.findElements(By
				.tagName("option"));

		for (WebElement webElement : sortingOptions) {

			if (sortingType.equals(StringUtils.trim(webElement.getText()))) {
				webElement.click();
				break;
			}
		}

		TimeUnit.MILLISECONDS.sleep(300);
	}

	/**
	 * This method is designed to select a Genre of the movie
	 * 
	 * @param genreType
	 */
	public void selectMovieGenre(String genreType) {

		WebElement genrePanel = getDriver().findElement(
				By.cssSelector("ul[class^=quicklinks]"));

		List<WebElement> liEls = genrePanel.findElements(By.tagName("li"));
		WebElement embeddedEL = null;
		for (WebElement webElement : liEls) {
			embeddedEL = webElement.findElement(By.tagName("a"));
			if (genreType.equals(StringUtils.trim(embeddedEL.getText()))) {
				embeddedEL.click();
				break;
			}
		}
	}

	/**
	 * This method is designed to get and return count of Movies displayed in
	 * "top 250"
	 * 
	 * @return
	 */
	public Integer getMoviesCount() {
		WebElement mainBodyMoviesTable = getDriver().findElement(
				By.cssSelector("tbody[class=lister-list]"));

		List<WebElement> allMoviesRows = mainBodyMoviesTable.findElements(By
				.tagName("tr"));

		return allMoviesRows.size();

	}

	/**
	 * This method is designed to get Movie count from displayed text
	 * 
	 * @return
	 */
	public Integer getMoviesCountFromText() {

		WebElement div = getDriver().findElement(
				By.cssSelector("div[class=desc]"));
		WebElement span = div.findElement(By.tagName("span"));
		String movieCount = span.getText();
		return Integer.parseInt(movieCount);

	}

	/**
	 * This method is designed to get and return count of Movies displayed by
	 * Genre
	 * 
	 * @return
	 */
	public Integer getMoviesCountByGenre() {
		String selector = "div[class=article]";
		WebElement articleDiv = new WebDriverWait(getDriver(), 60)
				.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector(selector)));

		WebElement mainBodyMoviesTable = articleDiv.findElement(By
				.cssSelector("table[class=results]"));

		List<WebElement> allMoviesRows = mainBodyMoviesTable.findElements(By
				.tagName("tr"));

		return allMoviesRows.size();

	}

}
