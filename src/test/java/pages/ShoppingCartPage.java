package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.Settings;
import support.WaitUtils;
import support.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage {
    WebDriver webDriver;
    WaitUtils waitUtils;
    String url = Settings.BASE_URL + "/cart";

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public ShoppingCartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.waitUtils = new WaitUtils(webDriver);
    }

    public ShoppingCartPage navigateToShoppingCart() {
        webDriver.navigate().to(url);
        return this;
    }

    public ShoppingCartPage areProductsInCart(List<Product> productList) {
        List<WebElement> elements = new ArrayList<>();

        //Search the cart to look for the items we added by their name
        productList.forEach(product -> {
            elements.add(webDriver.findElement(By.xpath("//div[@class='cart_item_label']//div[@class='inventory_item_name' and text()='" + product.getName() + "']")));
            System.out.println(product.getName() + " is visible");
        });

        //Assert list is not empty and the size matches the amount we received
        assert !elements.isEmpty() && elements.size() == productList.size();
        return this;
    }

    public boolean areProductsInCart(String productName) {
        //Same as above for only one product
        return webDriver.findElement(By.xpath("//div[@class='cart_item_label']//div[@class='inventory_item_name' and text()='" + productName + "']")).isDisplayed();
    }

    public CheckoutStepOnePage clickOnCheckoutButton() {
        waitUtils.waitForElementToBeClickable(checkoutButton).click();
        return new CheckoutStepOnePage(webDriver);
    }
}
