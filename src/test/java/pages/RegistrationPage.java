package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegistrationPage extends Elements {

    BasePage basePage = new BasePage(driver);
    String actualPageTitle;
    String expectedPageTitle = "ParaBank | Register for Free Online Account Access";
    String emptyField;
    boolean matchingPasswords;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    //Page method for verifying page title
    public void verifyRegistrationPageTitle() {
        actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "Expected and actual registration page title do not match");
    }

    //Page method for registration
    public void Registration(String firstName, String lastName, String address, String city, String state, String zipCode, String phone, String ssn, String username, String password, String repeatedPassword) {
        basePage.clickOnElement(registerLink);
        verifyRegistrationPageTitle();

        basePage.clearElement(firstNameInput);
        basePage.setValueToElement(firstNameInput, firstName);
        if (basePage.getAttributeFromElement(firstNameInput, "value").isEmpty()) {
            emptyField = "first name";
        }

        basePage.clickOnElement(lastNameInput);
        basePage.setValueToElement(lastNameInput, lastName);
        if (basePage.getAttributeFromElement(lastNameInput, "value").isEmpty()) {
            emptyField = "last name";
        }

        basePage.clearElement(addressInput);
        basePage.setValueToElement(addressInput, address);
        if (basePage.getAttributeFromElement(addressInput, "value").isEmpty()) {
            emptyField = "address";
        }


        basePage.clearElement(cityInput);
        basePage.setValueToElement(cityInput, city);
        if (basePage.getAttributeFromElement(cityInput, "value").isEmpty()) {
            emptyField = "city";
        }

        basePage.clickOnElement(stateInput);
        basePage.setValueToElement(stateInput, state);
        if (basePage.getAttributeFromElement(stateInput, "value").isEmpty()) {
            emptyField = "state";
        }

        basePage.clearElement(zipCodeInput);
        basePage.setValueToElement(zipCodeInput, zipCode);
        if (basePage.getAttributeFromElement(zipCodeInput, "value").isEmpty()) {
            emptyField = "zip code";
        }

        basePage.clearElement(phoneNumberInput);
        basePage.setValueToElement(phoneNumberInput, phone);

        basePage.clearElement(ssnInput);
        basePage.setValueToElement(ssnInput, ssn);
        if (basePage.getAttributeFromElement(ssnInput, "value").isEmpty()) {
            emptyField = "ssn";
        }

        basePage.clearElement(userNameInput);
        basePage.setValueToElement(userNameInput, username);
        if (basePage.getAttributeFromElement(userNameInput, "value").isEmpty()) {
            emptyField = "username";
        }

        basePage.clearElement(passwordInput);
        basePage.setValueToElement(passwordInput, password);
        if (basePage.getAttributeFromElement(passwordInput, "value").isEmpty()) {
            emptyField = "password";
        }

        basePage.clearElement(repeatedPasswordInput);
        basePage.setValueToElement(repeatedPasswordInput, repeatedPassword);
        if (basePage.getAttributeFromElement(repeatedPasswordInput, "value").isEmpty()) {
            emptyField = "repeated password";
        }

        if (repeatedPassword.equals(password)) {
            matchingPasswords = true;
        }

        basePage.clickOnElement(registerBtn);
    }

    //Page method for verifying registered user
    public void verifyRegisteredUser(String user, String names) {
        Assert.assertTrue(basePage.getTextFromElement(rightPanelTitle).contains("Welcome " + user), "Expected and actual registered user do not match");
        Assert.assertTrue(basePage.getTextFromElement(rightPanelMsg).contains("created successfully"), "Expected and actual registration message do not match");
        Assert.assertTrue(basePage.getTextFromElement(leftPanelTitle).contains("Welcome " + names), "Expected and actual login message do not match");
    }

    //Page method for verifying empty fields error message
    public void verifyEmptyFieldErrorMsg() {
        switch (emptyField) {
            case "first name":
                Assert.assertEquals(basePage.getTextFromElement(firstNameErrorMsg), "First name is required.", "Expected and actual empty first name error message do not match");
                break;
            case "last name":
                Assert.assertEquals(basePage.getTextFromElement(lastNameErrorMsg), "Last name is required.", "Expected and actual empty last name error message do not match");
                break;
            case "address":
                Assert.assertEquals(basePage.getTextFromElement(addressErrorMsg), "Address is required.", "Expected and actual empty address error message do not match");
                break;
            case "city":
                Assert.assertEquals(basePage.getTextFromElement(cityErrorMsg), "City is required.", "Expected and actual empty city error message do not match");
                break;
            case "state":
                Assert.assertEquals(basePage.getTextFromElement(stateErrorMsg), "State is required.", "Expected and actual empty state error message do not match");
                break;
            case "zip code":
                Assert.assertEquals(basePage.getTextFromElement(zipCodeErrorMsg), "Zip Code is required.", "Expected and actual empty zip code error message do not match");
                break;
            case "ssn":
                Assert.assertEquals(basePage.getTextFromElement(ssnErrorMsg), "Social Security Number is required.", "Expected and actual empty ssn error message do not match");
                break;
            case "username":
                Assert.assertEquals(basePage.getTextFromElement(usernameErrorMsg), "Username is required.", "Expected and actual empty username error message do not match");
                break;
            case "password":
                Assert.assertEquals(basePage.getTextFromElement(passwordErrorMsg), "Password is required.", "Expected and actual empty password error message do not match");
                break;
            case "repeated password":
                Assert.assertEquals(basePage.getTextFromElement(repeatedPasswordErrorMsg), "Password confirmation is required.", "Expected and actual empty repeated password error message do not match");
                break;
        }
    }

    //Page method for verifying mismatching passwords
    public void verifyMismatchingPasswordErrorMsg() {
        if (!matchingPasswords) {
            Assert.assertEquals(basePage.getTextFromElement(repeatedPasswordErrorMsg), "Passwords did not match.", "Expected and actual empty repeated password error message do not match");
        }
    }
}