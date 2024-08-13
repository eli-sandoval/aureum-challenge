package testscenarios;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import support.AbstractClass;
import support.data.UserData;

import java.util.function.Consumer;

@Test(groups = "Bonus")
public class BonusSpec extends AbstractClass {
    @Test(testName = "Visit element and all descendants, then print them")
    //We select an element and visit all its descendants
    public void checkoutEndToEnd() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .navigateToLoginPage();

        InventoryPage inventoryPage = loginPage
                .login(UserData.DEFAULT_USERNAME);

        //Create a simple callback that prints the text of the element
        Consumer<WebElement> callback = element -> System.out.println("Printing each element " + element.getText());

        //Call the recursive method with the root element
        inventoryPage.recursiveCallback(inventoryPage.rootDiv, callback);
    }
}
