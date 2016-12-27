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

public class ReviewScreen {
    private WebDriver driver = null;
    private long timeOut = 0;

    private final String REVIEW_SELECTPOLICY = "//form[@id=\"reviewform\"]//span[text()=\"Please select your policy\"]";
    private final String REVIEW_POLICY_HEALTH = "//form[@id=\"reviewform\"]//ul/li/a[@data-target=\"Health\"]";
    private final String REVIEW_MESSAGE = "//form[@id=\"reviewform\"]//textarea[@id=\"review-content\"]";
    private final String REVIEW_SUBMIT = "//form[@id=\"reviewform\"]//input[@value=\"Submit\"]";
    private final String REVIEW_MESSAGE_RECORDED = "//form[@id=\"reviewform\"]/div[2]/p";

    private final String REVIEW_FIRST_STAR = "//span[@id=\"overallRating\"]/a[1]";
    private final String REVIEW_SECOND_STAR = "//span[@id=\"overallRating\"]/a[2]";
    private final String REVIEW_THIRD_STAR = "//span[@id=\"overallRating\"]/a[3]";
    private final String REVIEW_FOURTH_STAR = "//span[@id=\"overallRating\"]/a[4]";
    private final String REVIEW_FIFTH_STAR = "//span[@id=\"overallRating\"]/a[5]";

    public ReviewScreen(WebDriver driver) {
        this.driver = driver;
        timeOut = 10;
    }

    public ReviewScreen(WebDriver driver, long timeOut) {
        this.driver = driver;
        this.timeOut = timeOut;
    }

    public void verifyCurrentScreenIsReviewScreen() {
        new WebDriverWait(driver, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(REVIEW_SELECTPOLICY)));
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.xpath(REVIEW_MESSAGE)));
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.xpath(REVIEW_SUBMIT)));

    }

    public void selectHealth() {

        try {
            driver.findElement(By.xpath(REVIEW_SELECTPOLICY)).click();
            WebElement healthItem = new WebDriverWait(driver, timeOut)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(REVIEW_POLICY_HEALTH)));
            healthItem.click();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void writeAReviewMessage(String reviewMessage) {
        try {
            WebElement reviewMessageElement = new WebDriverWait(driver, timeOut)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(REVIEW_MESSAGE)));
            reviewMessageElement.clear();
            reviewMessageElement.sendKeys(reviewMessage);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clickSubmit() {
        try {
            driver.findElement(By.xpath(REVIEW_SUBMIT)).click();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void verifyMessageAfterPosted(String message) {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(REVIEW_SUBMIT)));
            WebElement reviewMessageElement = new WebDriverWait(driver, timeOut)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(REVIEW_MESSAGE_RECORDED)));
            String currentText = reviewMessageElement.getText();
            currentText = currentText.replaceAll("\n", " ");
            Assert.assertEquals(currentText, message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void selectStar(int numberOfStars) {
        try {
            // 1. hover
            WebElement rating5Stars = new WebDriverWait(driver, timeOut)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(REVIEW_FIFTH_STAR)));

            // 2. click on number of stars
            switch (numberOfStars) {
                case 5:
                    // hover
                    Actions action = new Actions(driver);
                    action.moveToElement(rating5Stars).build().perform();
                    // verify hovered
                    new WebDriverWait(driver, timeOut).until(
                            ExpectedConditions.attributeContains(By.xpath(REVIEW_FIRST_STAR), "class", "bf-icon-star"));
                    new WebDriverWait(driver, timeOut).until(
                            ExpectedConditions.attributeContains(By.xpath(REVIEW_SECOND_STAR), "class", "bf-icon-star"));
                    new WebDriverWait(driver, timeOut).until(
                            ExpectedConditions.attributeContains(By.xpath(REVIEW_THIRD_STAR), "class", "bf-icon-star"));
                    new WebDriverWait(driver, timeOut).until(
                            ExpectedConditions.attributeContains(By.xpath(REVIEW_FOURTH_STAR), "class", "bf-icon-star"));
                    new WebDriverWait(driver, timeOut).until(
                            ExpectedConditions.attributeContains(By.xpath(REVIEW_FIFTH_STAR), "class", "bf-icon-star"));

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

}

