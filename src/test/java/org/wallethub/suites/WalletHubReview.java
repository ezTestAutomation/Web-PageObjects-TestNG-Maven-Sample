package org.wallethub.suites;

import org.testng.annotations.Test;
/**
 * Created by nguyen.pham on 12/27/16.
 */
import org.testng.annotations.Test;
import org.wallethub.resources.ProfileScreen;
import org.wallethub.resources.ReviewScreen;
import org.wallethub.resources.SignInScreen;

import config.ConfigurationManagement;
import config.WebDriverManagement;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class WalletHubReview {
    static WebDriver driver;
    WebDriverManagement webDriver = new WebDriverManagement(driver);
    SignInScreen signInScreen = null;
    ProfileScreen profileScreen = null;
    ReviewScreen reviewScreen = null;

    String urlReview = "";
    String urlSignIn = "";
    String urlProfile = "";
    String message = "";
    String username = "";
    String password = "";

    @Test
    public void WalletHubReviewVerify() throws InterruptedException {
        System.out.println(message);

        // 1. Log in
        signInScreen.signInWalletHub(username, password);

        // 2. Navigate to review site
        webDriver.navigateTo(urlReview);

        // 3. select 4 starts
        profileScreen.selectStars(4);

        // 4. select health & write a review message & submit & verify
        reviewScreen.verifyCurrentScreenIsReviewScreen();
        reviewScreen.selectHealth();
        Thread.sleep(2000); // wait till page is loaded
        reviewScreen.selectStar(5);
        reviewScreen.writeAReviewMessage(message);
        reviewScreen.clickSubmit();
        reviewScreen.verifyMessageAfterPosted(message);

        // 5. navigate to profile screen and verify
        String profileURL = parsingProfileURL(username);
        webDriver.navigateTo(profileURL);

        profileScreen.verifyActivity(message);

        Thread.sleep(2000);
    }

    @BeforeMethod
    public void beforeTest() {
        ConfigurationManagement config = new ConfigurationManagement();
        Map<String, String> configHashMap = null;
        try {
            configHashMap = config.getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browserType = configHashMap.get("browserType");
        long timeOut = Long.parseLong(configHashMap.get("timeOut"));

        urlReview = configHashMap.get("urlReview");
        urlSignIn = configHashMap.get("urlSignIn");
        urlProfile = configHashMap.get("urlProfile");
        username = configHashMap.get("walletHubUsername");
        password = configHashMap.get("walletHubPassword");
        message = configHashMap.get("walletHubMessage");

        driver = webDriver.startWebDriverInstance(urlSignIn, browserType);
        signInScreen = new SignInScreen(driver, timeOut);
        profileScreen = new ProfileScreen(driver, timeOut);
        reviewScreen = new ReviewScreen(driver, timeOut);

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            webDriver.takeAScreenshot();
            webDriver.closeBrowser();

        } else
            webDriver.closeBrowser();

    }

    private String parsingProfileURL(String emailAddress) {
        String username = emailAddress.split("@")[0];
        String url = urlProfile;
        url = url.replace("<yourusername>", username);
        return url;
    }

}

