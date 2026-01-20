package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Users;
import helpers.Hooks;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import java.io.File;
import java.io.IOException;

public class RegistrationPageTests extends Hooks {
    RegistrationPage registrationPage;
    ObjectMapper mapper;
    Users user;
    File jsonData;

    @Test(dataProvider = "ValidRegistrationData", dataProviderClass = MyDataProvider.class)
    public void successfulRegistration(String firstName, String lastName, String address, String city, String state, String zipCode, String phone, String ssn, String username, String password, String repeatedPassword) throws IOException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.Registration(firstName, lastName, address, city, state, zipCode, phone, ssn, username, password, repeatedPassword);

        mapper = new ObjectMapper();
        user = new Users(firstName, lastName, address, city, state, zipCode, phone, ssn, username, password, repeatedPassword); //expected Users object

        String sysPath = System.getProperty("user.dir");
        jsonData = new File(sysPath + "/src/test/java/data/user.json");
        mapper.writeValue(jsonData, user);

        String fullName = firstName + " " + lastName;
        registrationPage.verifyRegisteredUser(username, fullName);
    }

    @Test(dataProvider = "EmptyRegistrationData", dataProviderClass = MyDataProvider.class)
    public void emptyRegistration(String firstName, String lastName, String address, String city, String state, String zipCode, String phone, String ssn, String username, String password, String repeatedPassword) {
        registrationPage = new RegistrationPage(driver);
        registrationPage.Registration(firstName, lastName, address, city, state, zipCode, phone, ssn, username, password, repeatedPassword);
        registrationPage.verifyEmptyFieldErrorMsg();
    }

    @Test(dataProvider = "MismatchingPasswordsData", dataProviderClass = MyDataProvider.class)
    public void mismatchingPasswordsRegistration(String firstName, String lastName, String address, String city, String state, String zipCode, String phone, String ssn, String username, String password, String repeatedPassword) {
        registrationPage = new RegistrationPage(driver);
        registrationPage.Registration(firstName, lastName, address, city, state, zipCode, phone, ssn, username, password, repeatedPassword);
        registrationPage.verifyMismatchingPasswordErrorMsg();
    }
}