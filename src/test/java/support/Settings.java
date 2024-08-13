package support;

public class Settings {
    //Set base URL in a constant that we can use across the framework
    public static final String BASE_URL = "https://saucedemo.com/";
    //Set default browser so the driver still is created even if it is not specified
    //The choice is set through environment variables to avoid modifying the code with personal preferences
    public static final String DEFAULT_BROWSER = System.getProperty("driver", "selenium-chromium");
    //Same here. User's choice whether to run the tests headless
    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "false"));
    //For the default password we're using across all tests, it's important to never add the actual password value to the code
    //due to security measures. Here I set the default value just so that you are able to execute the code more easily
    //Ideally, the user would add the value to an environment variable
    public static final String DEFAULT_PASSWORD = System.getProperty("DEFAULT_PASSWORD", "secret_sauce");
    //Default timeout for waits, which can be modified here across the project or individually for each class on the constructor
    public static final int DEFAULT_TIMEOUT = 10;
    //Endpoint base url for API tests
    public static final String PET_ENDPOINTS_BASE_URL = "https://petstore.swagger.io/v2";
}
