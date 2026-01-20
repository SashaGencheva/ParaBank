package helpers;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Hooks {

    public static WebDriver driver;

    @BeforeTest
    @Parameters({"browser", "url"})
    public void setUp(String browserName, String link) {
        driver = BrowserFactory.getDriver(browserName);
        driver.get(link);
    }

    @AfterTest
    public void tearDown() {
        BrowserFactory.closeDriver();
    }
}