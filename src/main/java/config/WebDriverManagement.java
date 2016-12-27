package config;

/**
 * Created by nguyen.pham on 12/27/16.
 */

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class WebDriverManagement {
	private WebDriver driver = null;

	public WebDriverManagement(WebDriver driver) {
		this.driver = driver;
	}

	@SuppressWarnings("deprecation")
	public WebDriver startWebDriverInstance(String url, String browserType) {

		if (browserType.equals("gc")) {
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");
			driver = new ChromeDriver(ops);
		}
		if (browserType.equals("ff")) {
			// System.setProperty("webdriver.gecko.driver",
			// "/usr/bin/geckodriver");
			// driver = new MarionetteDriver();
			driver = new FirefoxDriver();
		}
		if (browserType.equals("ie")) {
			driver = new InternetExplorerDriver();
		}
		if (browserType.equals("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Invalid browser type...." + browserType);
		}

		driver.manage().window().maximize();
		driver.get(url);

		return driver;
	}

	public void takeAScreenshot() {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "/src/screenshots/";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();

		String filename = dateFormat.format(date).toString() + ".png";

		try {
			FileUtils.copyFile(source, new File(screenshotPath + filename));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeBrowser() {

		System.out.println("Closing browser...");
		driver.close();
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

}
