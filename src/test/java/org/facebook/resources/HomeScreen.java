package org.facebook.resources;

/**
 * Created by nguyen.pham on 12/27/16.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class HomeScreen {
    private WebDriver driver = null;
    private long timeOut = 0;

    private final String HOME_MESSAGEPANEL_MESSAGE_UNSELECTED = "//div[@id=\"contentArea\"]//textarea";
    private final String HOME_MESSAGEPANEL_MESSAGE = "//div[@id=\"contentArea\"]/div[@id=\"stream_pagelet\"]/div[@id=\"pagelet_composer\"]//div[@id=\"feedx_container\"]//div[@role=\"combobox\"]/div/div";
    private final String HOME_MESSAGEPANEL_POST = "//div[@id=\"contentArea\"]/div[@id=\"stream_pagelet\"]/div[@id=\"pagelet_composer\"]//div[@id=\"feedx_container\"]//div[@data-testid=\"react-composer-root\"]//button/span";
    private final String HOME_MESSAGE_AFTERPOSTED = "//div[contains(@id,\"feed_stream\")]/div[1]//p";

    public HomeScreen(WebDriver driver) {
        this.driver = driver;
    }

    public HomeScreen(WebDriver driver, long timeOut) {
        this.driver = driver;
        this.timeOut = timeOut;
    }

    public void verifyMessageAfterPosted(String message) {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.textToBe(By.xpath(HOME_MESSAGEPANEL_MESSAGE), ""));

            WebElement postedMessage = new WebDriverWait(driver, timeOut)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_MESSAGE_AFTERPOSTED)));
            String currentText = postedMessage.getText();
            Assert.assertEquals(currentText, message);
        } catch (Exception e) {
            Reporter.log("Error: " + e.getLocalizedMessage());

        }
    }

    public void verifyCurrentScreenIsHomeScreen() {
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_MESSAGEPANEL_MESSAGE_UNSELECTED)));
    }

    public void invokeMessage() {
        try {
            driver.findElement(By.xpath(HOME_MESSAGEPANEL_MESSAGE_UNSELECTED)).click();
        } catch (Exception e) {
            System.out.println("Couldn't find Message to invoke on Home screen");
            System.out.println(e);
        }
    }

    public void inputMessage(String message) {
        try {
            WebElement a = new WebDriverWait(driver, timeOut)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_MESSAGEPANEL_MESSAGE_UNSELECTED)));
            a.sendKeys(message);
            // driver.findElement(By.xpath(HOME_MESSAGEPANEL_MESSAGE)).sendKeys(message);
        } catch (Exception e) {
            System.out.println("Couldn't found message on Home Screen!");
            System.out.println(e);
        }
    }

    public void clickPost() {
        try {
            driver.findElement(By.xpath(HOME_MESSAGEPANEL_POST)).click();
        } catch (Exception e) {
            System.out.println("Couldn't click on Post button on Home Screen!");
            System.out.println(e);
        }
    }

}

