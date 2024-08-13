package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.Settings;
import support.WaitUtils;

public class CheckoutCompletePage {
    WebDriver webDriver;
    WaitUtils waitUtils;
    String url = Settings.BASE_URL + "/checkout-complete";

    @FindBy(className = "complete-header")
    WebElement orderCompleteHeader;

    public CheckoutCompletePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.waitUtils = new WaitUtils(webDriver);
    }

    public CheckoutCompletePage navigateToCheckoutStepTwoPage() {
        webDriver.navigate().to(url);
        return this;
    }

    public CheckoutCompletePage validateOrderCompleteMessage() {
        waitUtils.waitForElementToBeVisible(orderCompleteHeader);
        return this;
    }
}
