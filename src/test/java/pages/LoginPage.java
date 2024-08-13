package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.Settings;
import support.WaitUtils;

import static support.Settings.DEFAULT_PASSWORD;

public class LoginPage {
    WebDriver webDriver;
    WaitUtils waitUtils;
    String url = Settings.BASE_URL;

    @FindBy(id = "user-name")
    WebElement userNameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.waitUtils = new WaitUtils(webDriver);
    }

    public LoginPage navigateToLoginPage() {
        webDriver.navigate().to(url);
        return this;
    }

    public LoginPage login(String user, String password) {
        waitUtils.waitForElementToBeClickable(userNameField);
        userNameField.sendKeys(user);
        waitUtils.waitForElementToBeClickable(passwordField);
        passwordField.sendKeys(password);
        loginButton.click();

        waitUtils.waitForUrlToContain("/inventory");
        return this;
    }

    //Overloading the Login method above so that we don't need to send the password each time for security reasons.
    public InventoryPage login(String user) {
        login(user, DEFAULT_PASSWORD);
        return new InventoryPage(webDriver);
    }
}
