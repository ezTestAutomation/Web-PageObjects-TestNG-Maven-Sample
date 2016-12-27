package org.facebook.resources;

/**
 * Created by nguyen.pham on 12/27/16.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInScreen {
    private WebDriver driver = null;
    private long timeOut = 0;

    private final String SIGNIN_EMAILADDRESS = "//input[@id=\"email\"]";
    private final String SIGNIN_PASSWORD = "//input[@id=\"pass\"]";
    private final String SIGNIN_SIGNIN = "//label[@id=\"loginbutton\"]";

    public LogInScreen(WebDriver driver) {
        this.driver = driver;
    }

    public LogInScreen(WebDriver driver, long timeOut) {
        this.driver = driver;
        this.timeOut = timeOut;
    }

    public void verifyCurrentScreenIsSignInScreen() {
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SIGNIN_EMAILADDRESS)));
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SIGNIN_PASSWORD)));
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.xpath(SIGNIN_SIGNIN)));
    }

    public void inputEmailAddress(String emailAddress) {
        try {
            driver.findElement(By.xpath(SIGNIN_EMAILADDRESS)).sendKeys(emailAddress);
        } catch (Exception e) {
            System.out.println("Couldn't input email address!");
            System.out.println(e);
        }
    }

    public void inputPassword(String password) {
        try {
            driver.findElement(By.xpath(SIGNIN_PASSWORD)).sendKeys(password);
        } catch (Exception e) {
            System.out.println("Couldn't input password!");
            System.out.println(e);
        }
    }

    public void clickSignIn() {
        try {
            driver.findElement(By.xpath(SIGNIN_SIGNIN)).click();
        } catch (Exception e) {
            System.out.println("Couldn't click on Sign In button!");
            System.out.println(e);
        }
    }
}
