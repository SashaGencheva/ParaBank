package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class MyDataProvider {

    //Method that provides valid login data
    @DataProvider(name = "ValidCredentials")
    public static Object[][] getValidCredentials() {
        return new Object[][]{{"john", "demo"}};
    }

    //Method that provides invalid login data
    @DataProvider(name = "InvalidCredentials")
    public static Object[][] getInvalidCredentials() {
        return new Object[][]{{"ivan", "ivan123"}, {"john", "   "}, {"   ", "demo"}, {"", ""}, {"john", ""}, {"", "demo"}};
    }

    //Method that provides valid registration data
    @DataProvider(name = "ValidRegistrationData")
    public static Object[][] getValidRegistrationData() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String address = faker.address().streetAddress();
        String city = faker.address().cityName();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode();
        String phone = faker.phoneNumber().cellPhone();
        String ssn = "123-45-6789";
        String username = faker.name().username();
        String password = faker.internet().password();
        String repeatedPassword = password;

        return new Object[][]{{firstName, lastName, address, city, state, zipCode, phone, ssn, username, password, repeatedPassword}};
    }

    //Method that provides empty registration data
    @DataProvider(name = "EmptyRegistrationData")
    public static Object[][] getEmptyRegistrationData() {
        return new Object[][]{{"","","","","","","","","","",""},
                {"", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "BG", "1000", "0888123456", "123-45-6789", "simeon.iv", "simeon.iv123", "simeon.iv123"},
                {"Simeon", "Ivanov", "", "Sofia", "BG", "1000", "0888123456", "123-45-6789", "simeon.iv", "simeon.iv123", "simeon.iv123"},
                {"Simeon", "Ivanov", "\"Pirin\" 11 Str", "", "BG", "1000", "0888123456", "123-45-6789", "simeon.iv", "simeon.iv123", "simeon.iv123"},
                {"Simeon", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "", "1000", "0888123456", "123-45-6789", "simeon.iv", "simeon.iv123", "simeon.iv123"},
                {"Simeon", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "BG", "", "0888123456", "123-45-6789", "simeon.iv", "simeon.iv123", "simeon.iv123"},
                {"Simeon", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "BG", "1000", "0888123456", "", "simeon.iv", "simeon.iv123", "simeon.iv123"},
                {"Simeon", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "BG", "1000", "0888123456", "123-45-6789", "", "simeon.iv123", "simeon.iv123"},
                {"Simeon", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "BG", "1000", "0888123456", "123-45-6789", "simeon.iv", "", "simeon.iv123"},
                {"Simeon", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "BG", "1000", "0888123456", "123-45-6789", "simeon.iv", "simeon.iv123", ""}};
    }

    //Method that provides mismatching passwords data
    @DataProvider(name = "MismatchingPasswordsData")
    public static Object[][] getMismatchingPasswordsData() {
        return new Object[][]{{"Simeon", "Ivanov", "\"Pirin\" 11 Str", "Sofia", "BG", "1000", "0888123456", "123-45-6789", "simeon.iv", "simeon.iv123", "simeon.iv111"}};
    }
}