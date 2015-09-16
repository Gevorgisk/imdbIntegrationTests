package com.imdb.uitest.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public final class Constants {

	private static final String PROPERTIES_FILE_NAME = "test.properties";
	private static final String LOAD_DELAY_PARAM = "load.delay";
	private static final String INDEX_PAGE_URL_PARAM = "index.page.url";
	private static final String BROWSER_WIDTH_PERCENT_PARAM = "browser.width.percent";
	private static final String BROWSER_HEIGHT_PERCENT_PARAM = "browser.height.percent";

	public static double BROWSER_WIDTH_PERCENT;
	public static double BROWSER_HEIGHT_PERCENT;
	public static String INDEX_PAGE_URL;
	public static String OBJECT_TEXT;
	public static String BROWSER_INFO;
	public static String BASE_URL;
	
	public static String LOAD_DELAY;
	public static final int TEST_TIMEOUT = 600000;
	static {
		try {
			URL url = Constants.class.getClassLoader().getResource(PROPERTIES_FILE_NAME);
			URI uri = new URI(url.toString());
			FileInputStream fileInputStream = new FileInputStream(uri.getPath());
			Properties properties = new Properties();
			properties.load(fileInputStream);
			BASE_URL = "http://imdb.com";
			BASE_URL+= "/";
			LOAD_DELAY = properties.getProperty(LOAD_DELAY_PARAM,"50000");
			INDEX_PAGE_URL = properties.getProperty(INDEX_PAGE_URL_PARAM);
			BROWSER_WIDTH_PERCENT = Double.valueOf(properties.getProperty(BROWSER_WIDTH_PERCENT_PARAM,"0.8"));
			BROWSER_HEIGHT_PERCENT = Double.valueOf(properties.getProperty(BROWSER_HEIGHT_PERCENT_PARAM,"0.9"));

		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
