package org.wallethub.resources;

/**
 * Created by nguyen.pham on 12/27/16.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInScreen {
    private WebDriver driver = null;
    private long timeOut = 0;

    private final String LOGIN_EMAILADDRESS = "//input[@placeholder=\"Email Address\"]";
    private final String LOGIN_PASSWORD = "//input[@placeholder=\"Password\"]";
    private final String LOGIN_LOGIN = "//button/span[text()=\"Login\"]";

    public SignInScreen(WebDriver driver) {
        this.driver = driver;
        timeOut = 10;
    }

    public SignInScreen(WebDriver driver, long timeOut) {
        this.driver = driver;
        this.timeOut = timeOut;
    }

    public void signInWalletHub(String username, String password) {
        verifyCurrentScreenIsLogInScreen();
        inputEmailAddress(username);
        inputPassword(password);
        clickLogIn();
        verifyLoginSuccessful();

    }

    public void verifyCurrentScreenIsLogInScreen() {
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGIN_EMAILADDRESS)));
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGIN_PASSWORD)));
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGIN_LOGIN)));
    }

    public void inputEmailAddress(String emailAddress) {
        try {
            driver.findElement(By.xpath(LOGIN_EMAILADDRESS)).sendKeys(emailAddress);
        } catch (Exception e) {
            System.out.println("Couldn't find email address!");
            System.out.println(e);
        }
    }

    public void inputPassword(String password) {
        try {
            driver.findElement(By.xpath(LOGIN_PASSWORD)).sendKeys(password);
        } catch (Exception e) {
            System.out.println("Couldn't find password textfied!");
            System.out.println(e);
        }
    }

    public void clickLogIn() {
        try {
            driver.findElement(By.xpath(LOGIN_LOGIN)).click();
        } catch (Exception e) {
            System.out.println("Couldn't find Login button");
            System.out.println(e);
        }
    }

    public void verifyLoginSuccessful() {
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOGIN_EMAILADDRESS)));
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOGIN_PASSWORD)));
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOGIN_LOGIN)));
    }

}
