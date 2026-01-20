package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends Elements {

    BasePage basePage = new BasePage(driver);
    String actualPageTitle;
    String expectedPageTitle = "ParaBank | Welcome | Online Banking";
    String errorMessage;
    String errorMessageTitle;
    boolean emptyCredentials;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Page method for verifying page title
    public void verifyPageTitle() {
        actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "Expected and actual page title do not match");
    }

    //Page method for login
    public void Login(String username, String password) {
        Assert.assertTrue(basePage.getTextFromElement(userNameLabel).contains("User"), "Expected and actual username label do not match");
        basePage.clearElement(userNameField);
        basePage.setValueToElement(userNameField, username);

        Assert.assertTrue(basePage.getTextFromElement(passwordLabel).contains("Password"), "Expected and actual password label do not match");
        basePage.clearElement(passwordField);
        basePage.setValueToElement(passwordField, password);

        if (basePage.getAttributeFromElement(userNameField, "value").isEmpty() || basePage.getAttributeFromElement(passwordField, "value").isEmpty()) {
            emptyCredentials = true;
        }
        Assert.assertTrue(basePage.getAttributeFromElement(loginBtn, "value").equalsIgnoreCase("log in"), "Expected and actual login button title do not match");
        basePage.clickOnElement(loginBtn);
    }

    //Page method for verifying error message
    public void verifyErrorMessage() {
        expectedPageTitle = "ParaBank | Error";
        errorMessage = basePage.getTextFromElement(errorMsg);
        errorMessageTitle = basePage.getTextFromElement(errorTitle);

        verifyPageTitle();
        Assert.assertTrue(errorMessageTitle.contains("Error"), "Expected and actual error message title do not match");
        Assert.assertEquals(errorMsg.getCssValue("color"), "rgba(255, 0, 0, 1)", "Expected and actual error message color do not match");

        if (emptyCredentials) {
            Assert.assertTrue(errorMessage.contains("Please enter a username and password"), "Expected and actual error message do not match");
        } else {
            Assert.assertTrue(errorMessage.contains("The username and password could not be verified"), "Expected and actual error message do not match");
        }
    }
}