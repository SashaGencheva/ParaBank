package tests;

import helpers.Hooks;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTests extends Hooks {
    LoginPage loginPage;
    HomePage homePage;

    @Test(priority = 0, dataProvider = "ValidCredentials", dataProviderClass = MyDataProvider.class)
    public void successfulLogin(String userName, String password) {
        loginPage = new LoginPage(driver);
        loginPage.verifyPageTitle();
        loginPage.Login(userName, password);

        homePage = new HomePage(driver);
        homePage.verifyLoginMessage();
    }

    @Test(dependsOnMethods = {"successfulLogin"})
    public void successfulLogout() {
        homePage.Logout();
        loginPage.verifyPageTitle();
    }

    @Test(priority = 1, dataProvider = "InvalidCredentials", dataProviderClass = MyDataProvider.class)
    public void unsuccessfulLogin(String userName, String password) {
        loginPage = new LoginPage(driver);
        loginPage.Login(userName, password);
        loginPage.verifyErrorMessage();
    }
}