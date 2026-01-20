package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends Elements {

    BasePage basePage = new BasePage(driver);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Page method for verifying logged user
    public void verifyLoginMessage() {
        Assert.assertTrue(basePage.getTextFromElement(leftPanelTitle).contains("Welcome "), "Expected and actual login message do not match");
    }

    //Page method for logout
    public void Logout() {
        Assert.assertTrue(basePage.getTextFromElement(logoutBtn).equalsIgnoreCase("log out"), "Expected and actual logout button title do not match");
        basePage.clickOnElement(logoutBtn);
    }
}