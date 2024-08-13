package testscenarios;

import org.testng.annotations.Test;
import pages.LoginPage;
import support.AbstractClass;
import support.data.UserData;
import support.model.User;

@Test(groups = "ShoppingCart")
public class ShoppingCartSpec extends AbstractClass {

    @Test(testName = "User adds product to shopping cart")
    //User adds products to shopping cart and the products are validated in the shopping cart page
    public void userAddsProductToShoppingCart() {
        User user = UserData.newValidUserWithListOfProducts();

        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .navigateToLoginPage()
                .login(user.getUsername())

                //Fill shopping cart with multiple products
                .addProductToCart(user.getProduct())
                .clickOnShoppingCart()

                //Validate all those products are present
                .areProductsInCart(user.getProduct());
    }
}
