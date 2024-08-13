package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import support.Settings;
import support.WaitUtils;
import support.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InventoryPage {
    WebDriver webDriver;
    WaitUtils waitUtils;
    String url = Settings.BASE_URL + "/inventory";

    @FindBy(id = "shopping_cart_container")
    WebElement shoppingCartButton;

    @FindBy(className = "product_sort_container")
    WebElement sortingSelector;

    @FindBy(id = "root")
    public WebElement rootDiv;

    public InventoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.waitUtils = new WaitUtils(webDriver);
    }

    public InventoryPage navigateToInventory() {
        webDriver.navigate().to(url);
        return this;
    }

    public InventoryPage addProductToCart(String productName) {
        //Find the add to cart button for the specific product name we receive by using child and ancestor elements
        WebElement addToCartButtonForProduct = webDriver.findElement(By.xpath("//div[@class='inventory_item']//div[@data-test='inventory-item-name' and text() = '" + productName + "']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']"));
        waitUtils.waitForElementToBeClickable(addToCartButtonForProduct);
        addToCartButtonForProduct.click();
        return this;
    }

    public InventoryPage addProductToCart(List<Product> productList) {
        //Overload method above to receive multiple products and add them all to cart
        productList.forEach(product ->
                addProductToCart(product.getName()));
        return this;
    }

    public InventoryPage sortBy(String option) {
        Select dropdown = new Select(sortingSelector);
        dropdown.selectByValue(option);
        return this;
    }

    public boolean verifyPriceHiToLowOrder() {
        List<Double> prices = new ArrayList<>();
        //Find all the products on screen
        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='inventory_item']//div[@data-test='inventory-item-name']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']"));
        //Add each price of each product to a list
        products.forEach(element -> prices.add(Double.valueOf(element.getText().replace("$", ""))));

        //Iterate through the list and compare values to see if they are in order
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyNameZToA() {
        List<String> names = new ArrayList<>();
        //Find all the products on screen
        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='inventory_item']//div[@data-test='inventory-item-name']"));
        //Add all names to a list
        products.forEach(element -> names.add(element.getText()));

        //Iterate through the list and compare values to see if they are in order
        for (int i = 0; i < names.size() - 1; i++) {
            if (names.get(i).compareTo(names.get(i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    public ShoppingCartPage clickOnShoppingCart() {
        waitUtils.waitForElementToBeClickable(shoppingCartButton).click();
        return new ShoppingCartPage(webDriver);
    }

    //The bonus task was a bit difficult. The hard part was understanding the concept of what was being asked.
    //Method receives the room element through parameters
    public InventoryPage recursiveCallback(WebElement webElement, Consumer<WebElement> callback) {
        //Perform operation on callback
        callback.accept(webElement);

        //Find all child elements for root element, basically everything
        List<WebElement> childElements = webElement.findElements(By.xpath("./*"));

        //For each of them, execute the callback and call their children, again and again until there are no more children
        for (WebElement child : childElements) {
            recursiveCallback(child, callback);
        }
        return this;
    }
}
