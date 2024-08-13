package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

//Class that gives life to the driver
public class DriverFactory {
    static DesiredCapabilities capabilities = new DesiredCapabilities();
    static String url;
    static WebDriver webDriver;

    protected static WebDriver createWebDriver() {
        //Depending on the container we want to use we need different capabilities
        switch (Settings.DEFAULT_BROWSER) {
            case "selenium-chrome":
                url = "http://localhost:4446";
                capabilities = determineWebBrowserType("chrome");
            case "selenium-firefox":
                url = "http://localhost:4445";
                capabilities = determineWebBrowserType("firefox");
                //This one I needed because my mac runs an M1 chip and selenium-chrome doesn't like that.
                //It uses the same capabilities as the chrome one above
            case "selenium-chromium":
                url = "http://localhost:4444";
                capabilities = determineWebBrowserType("chrome");
        }

        System.out.println("connecting to selenium instance at: " + url);

        try {
            //Based on the container of one's choosing, the webdriver is started with the capabilities below
            webDriver = new RemoteWebDriver(new URL(url), capabilities);
            return webDriver;
        } catch (WebDriverException | MalformedURLException e) {
            System.out.println("Driver creation failed with message: ${e.message}");
        }
        return webDriver;
    }


    private static DesiredCapabilities determineWebBrowserType(String property) {
        //Chrome-chromium capabilities
        if (property.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            if (Settings.HEADLESS) {
                chromeOptions.addArguments("--headless");
            }
            //To avoid memory issues
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--start-maximized");
            capabilities.merge(chromeOptions);

            //Firefox capabilities
        } else if (property.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (Settings.HEADLESS) {
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--start-maximized");
            }
            capabilities.merge(firefoxOptions);
        }
        return capabilities;
    }
}
