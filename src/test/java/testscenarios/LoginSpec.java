package testscenarios;

import org.testng.annotations.Test;
import pages.LoginPage;
import support.AbstractClass;
import support.data.UserData;
import support.model.User;

@Test(groups = "Login")
public class LoginSpec extends AbstractClass {

    @Test(testName = "Login with valid credentials")
    //User logs in with valid credentials and is redirected automatically to the inventory page.
    public void loginWithUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        //Simple login and url validation within the method
        loginPage
                .navigateToLoginPage()
                .login(UserData.DEFAULT_USERNAME);
    }
}
