package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static support.Settings.DEFAULT_TIMEOUT;

//Centralized class for waits. No Thread.sleep anywhere in the code.
public class WaitUtils {
    WebDriver webDriver;
    WebDriverWait wait;

    //Constructor with timeout
    public WaitUtils(WebDriver driver, int timeout) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    //Constructor with default timeout
    //I always call this one unless a class requires otherwise
    public WaitUtils(WebDriver driver) {
        this(driver, DEFAULT_TIMEOUT);
    }

    //Always wait for elements to be clickable before performing action
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Always wait for elements to be visible before performing action
    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Wait until page loads and url contains desdired String
    public boolean waitForUrlToContain(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }
}
