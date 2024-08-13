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

public class CheckoutStepTwoPage {
    WebDriver webDriver;
    WaitUtils waitUtils;
    String url = Settings.BASE_URL + "/checkout-step-two";

    @FindBy(className = "summary_subtotal_label")
    WebElement itemSubTotalLabel;

    @FindBy(className = "summary_tax_label")
    WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    WebElement totalLabel;

    @FindBy(id = "finish")
    WebElement finishButton;

    public CheckoutStepTwoPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.waitUtils = new WaitUtils(webDriver);
    }

    public pages.CheckoutStepTwoPage navigateToCheckoutStepTwoPage() {
        webDriver.navigate().to(url);
        return this;
    }

    public CheckoutStepTwoPage verifyProducts(List<Product> productList) {
        List<WebElement> elements = new ArrayList<>();

        //We iterate through the list of products we received and add them to a list of elements using product name and product price
        productList.forEach(product -> {
            elements.add(webDriver.findElement(By.xpath("//div[@class='cart_list']//div[@data-test='inventory-item-name' and text() = '" + product.getName() + "']/ancestor::div[@class='cart_item']//div[text()='"+ product.getPrice()+"']")));
            System.out.println(product.getName() + " is visible with price $" + product.getPrice());
        });
        //assert that the list is NOT empty and that the received list of products has the same size as the amount of products on screen
        assert !elements.isEmpty() && elements.size() == productList.size();
        return this;
    }

    public CheckoutStepTwoPage verifyTotalPrice(List<Product> productList) {
        //Sum all the prices from the list of products we receive. That's the subtotal
        double expectedTotalPrice = productList.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        //Assert the calculated subtotal = the subtotal on screen
        assert itemSubTotalLabel.getText().equals("Item total: $" + expectedTotalPrice);

        //Calculate tax
        double tax = expectedTotalPrice * 0.08;
        //Round it up to two decimals
        String roundedTax = String.format("%.2f", tax);
        //Assert the calculated tax = the tax on screen
        assert taxLabel.getText().equals("Tax: $" + roundedTax);

        //sum of rounded tax + subtotal
        double total = Double.parseDouble(roundedTax) + expectedTotalPrice;
        //Assert the calculated total = the total on screen
        assert totalLabel.getText().equals("Total: $"+ total);

        return this;
    }

    public CheckoutCompletePage clickFinishButton() {
        waitUtils.waitForElementToBeClickable(finishButton).click();
        return new CheckoutCompletePage(webDriver);
    }
}
