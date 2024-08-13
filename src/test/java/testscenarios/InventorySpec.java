package testscenarios;

import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import support.AbstractClass;
import support.Constants;
import support.data.UserData;

@Test(groups = "Inventory")
public class InventorySpec extends AbstractClass {

    @Test(testName = "User sorts inventory by price high to low")
    //User sorts inventory by price high to low. Assertions ensure the all the products are in the correct order.
    public void sortByPriceHighLow() {
        LoginPage loginPage = new LoginPage(driver)
                .navigateToLoginPage();

        InventoryPage inventoryPage = loginPage
                .login(UserData.DEFAULT_USERNAME)
                .sortBy(Constants.INVENTORY_SORT_BY_PRICE_HI_LO);

        //Verify sorting by price High Low
        assert inventoryPage.verifyPriceHiToLowOrder();
    }

    @Test(testName = "User sorts inventory by name Z to A")
    //User sorts inventory by name Z to A. Assertions ensure the all the products are in the correct order.
    public void sortByNameZToA() {
        LoginPage loginPage = new LoginPage(driver)
                .navigateToLoginPage();

        InventoryPage inventoryPage = loginPage
                .login(UserData.DEFAULT_USERNAME)
                .sortBy(Constants.INVENTORY_SORT_BY_NAME_Z_A);

        //Verify sorting by name Z-A
        assert inventoryPage.verifyNameZToA();
    }
}
