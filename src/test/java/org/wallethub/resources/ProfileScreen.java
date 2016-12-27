package org.wallethub.resources;

/**
 * Created by nguyen.pham on 12/27/16.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProfileScreen {
    private WebDriver driver = null;
    private long timeOut = 0;
    private final String HOME_RATING_UNSELECTED = "//div[@class=\"maininfo\"]//span[contains(@class,\"wh-rating\")]";
    private final String HOME_RATING_4STARTS = "//div[@class=\"maininfo\"]//div[@class=\"wh-rating-choices\"]/div/a[text()=\"4\"]";
    private final String HOME_RATING_5STARTS = "//div[@class=\"maininfo\"]//div[@class=\"wh-rating-choices\"]/div/a[text()=\"5\"]";
    private final String HOME_RATING_5STARTS_MESSAGE = "//div[@class=\"maininfo\"]//div[@class=\"wh-rating-choices\"]/div/em";
    private final String PROFILE_MESSAGE = "//div[@class=\"activities\"]/div[1]/div[@class=\"content\"]/div[@class=\"feed\"]//p";

    public ProfileScreen(WebDriver driver) {
        this.driver = driver;
        timeOut = 10;
    }

    public ProfileScreen(WebDriver driver, long timeOut) {
        this.driver = driver;
        this.timeOut = timeOut;
    }

    public void selectStars(int numberOfStars) {
        try {
            // 1. hover
            WebElement ratingUnselected = new WebDriverWait(driver, timeOut)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_RATING_UNSELECTED)));
            Actions action = new Actions(driver);
            action.moveToElement(ratingUnselected).build().perform();

            // 2. click on number of stars
            switch (numberOfStars) {
                case 4:
                    WebElement rating4Stars = new WebDriverWait(driver, timeOut)
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_RATING_4STARTS)));
                    rating4Stars.click();
                    break;
                case 5:
                    // hover
                    action.moveToElement(ratingUnselected).build().perform();
                    // verify hovered
                    // new WebDriverWait(driver,
                    // timeOut).until(ExpectedConditions.attributeContains(By.xpath(HOME_RATING_5STARTS),
                    // "class", "hover"));
                    // new WebDriverWait(driver,
                    // timeOut).until(ExpectedConditions.textToBe(By.xpath(HOME_RATING_5STARTS_MESSAGE),
                    // "Your Rating: Excellent"));
                    WebElement rating5Stars = new WebDriverWait(driver, timeOut)
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HOME_RATING_5STARTS)));
                    rating5Stars.click();
                    break;
                default:
                    System.out.println("Not implemented");
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void verifyActivity(String message) {
        WebElement currentMessageElement = new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(PROFILE_MESSAGE)));
        String currentMessage = currentMessageElement.getText();
        currentMessage = currentMessage.replace("�Show more", "");
        currentMessage = currentMessage.replace("…Show more", "");
        System.out.println(currentMessage);
        Assert.assertTrue(message.contains(currentMessage));
    }

}

