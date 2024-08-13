package support;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

//Abstract class that handles setup and teardown of the webdriver execution
public class AbstractClass extends DriverFactory {
    protected static WebDriver driver;

    //Before each class is executed, we create the webdriver
    @BeforeClass
    public void setUp() {
        System.out.println("starting test run on environment " + Settings.BASE_URL);
        driver = createWebDriver();
    }

    //After each class, we tear it down
    @AfterClass
    public void tearDown() {
        System.out.println("Shutting down driver");
        if (driver != null) {
            driver.quit();
        }
    }

    //After each method we take a screenshot of the last screen we see when the test finishes
    @AfterMethod
    public void afterMethod() {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("screenshot.png");
            FileUtils.copyFile(screenshot, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static WebDriver getDriver() {
        return driver;
    }

}
