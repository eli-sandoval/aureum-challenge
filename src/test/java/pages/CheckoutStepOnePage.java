package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.Settings;
import support.WaitUtils;

public class CheckoutStepOnePage {
    WebDriver webDriver;
    WaitUtils waitUtils;
    String url = Settings.BASE_URL + "/checkout-step-one";

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement zipCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    public CheckoutStepOnePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.waitUtils = new WaitUtils(webDriver);
    }

    public CheckoutStepOnePage navigateToCheckoutStepOnePage() {
        webDriver.navigate().to(url);
        return this;
    }

    public CheckoutStepOnePage fillMandatoryFields(String firstName, String lastName, int zipCode) {
        waitUtils.waitForElementToBeVisible(firstNameField).sendKeys(firstName);
        waitUtils.waitForElementToBeVisible(lastNameField).sendKeys(lastName);
        waitUtils.waitForElementToBeVisible(zipCodeField).sendKeys(String.valueOf(zipCode));
        return this;
    }

    public CheckoutStepTwoPage clickOnContinueButton() {
        waitUtils.waitForElementToBeClickable(continueButton).click();
        return new CheckoutStepTwoPage(webDriver);
    }
}
