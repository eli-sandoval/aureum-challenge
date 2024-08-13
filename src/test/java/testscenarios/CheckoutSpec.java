package testscenarios;

import org.testng.annotations.Test;
import pages.*;
import support.AbstractClass;
import support.data.UserData;
import support.model.User;

@Test(groups = "Checkout")
public class CheckoutSpec extends AbstractClass {

    @Test(testName = "User completes checkout process")
    //User completes checkout process. With more time to deliver this framework, I would've used gherkin (BDD).
    //Code could be re-used and there would be no need to repeat code that's already present in other classes.
    public void checkoutEndToEnd() {
        User user = UserData.newValidUserWithListOfProducts();

        LoginPage loginPage = new LoginPage(driver)
                .navigateToLoginPage();

        InventoryPage inventoryPage = loginPage
                .login(user.getUsername());

        ShoppingCartPage shoppingCartPage = inventoryPage
                .addProductToCart(user.getProduct())
                .clickOnShoppingCart();

        CheckoutCompletePage checkoutStepTwoPage = shoppingCartPage
                //Validate products are in cart
                .areProductsInCart(user.getProduct())
                .clickOnCheckoutButton()
                .fillMandatoryFields(user.getName(), user.getLastName(), user.getZipcode())
                .clickOnContinueButton()
                //Verify same products are in the checkout summary
                .verifyProducts(user.getProduct())
                //Verify totals
                .verifyTotalPrice(user.getProduct())
                .clickFinishButton()
                //Finish purchase
                .validateOrderCompleteMessage();
    }
}
