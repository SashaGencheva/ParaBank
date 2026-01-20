package tests;

import api.Converter;
import api.Requests;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Users;
import helpers.HooksApi;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LoginApiTests extends HooksApi {
    String url;
    String sysPath;
    File jsonData;
    ObjectMapper mapper;
    Users user;
    Response response;

    int actualStatusCode;
    String actualStatusLine;
    String actualHeaderValue;
    int actualUserId;
    String actualFirstName;
    String actualLastName;
    String actualAddress;
    String actualCity;
    String actualState;
    String actualZipCode;
    String actualPhone;
    String actualSsn;
    String expectedFirstName;
    String expectedLastName;
    String expectedAddress;
    String expectedCity;
    String expectedState;
    String expectedZipCode;
    String expectedPhone;
    String expectedSsn;

    @Test
    public void successfulLoginApiTest(ITestContext context) throws IOException {
        sysPath = System.getProperty("user.dir");
        jsonData = new File(sysPath + "/src/test/java/data/user.json");

        //Jackson API
        mapper = new ObjectMapper();
        user = mapper.readValue(jsonData, Users.class); //expected Users object

        url = loginApiUrl + user.getUsername() + "/" + user.getPassword();
        System.out.println("=================== Successful Login ===================");
        System.out.println("GET Request: " + url);

        //Call the API
        response = Requests.get(url);
        response.then().log().all();
        Converter.xmlToJson(response);

        response.then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("userDetailsXmlSchema.xsd"));

        actualUserId = response.xmlPath().getInt("customer.id");
        user.setId(actualUserId);
        mapper.writeValue(jsonData, user);

        //API Chaining
        context.getSuite().setAttribute("userId", actualUserId);

        actualFirstName = response.xmlPath().getString("customer.firstName");
        actualLastName = response.xmlPath().getString("customer.lastName");
        actualAddress = response.xmlPath().getString("customer.address.street");
        actualCity = response.xmlPath().getString("customer.address.city");
        actualState = response.xmlPath().getString("customer.address.state");
        actualZipCode = response.xmlPath().getString("customer.address.zipCode");
        actualPhone = response.xmlPath().getString("customer.phoneNumber");
        actualSsn = response.xmlPath().getString("customer.ssn");

        expectedFirstName = user.getFirstName();
        expectedLastName = user.getLastName();
        expectedAddress = user.getAddress();
        expectedCity = user.getCity();
        expectedState = user.getState();
        expectedZipCode = user.getZipCode();
        expectedPhone = user.getPhone();
        expectedSsn = user.getSsn();

        actualStatusCode = Requests.getResponseCode(response);
        actualStatusLine = Requests.getResponseStatusLine(response);
        actualHeaderValue = Requests.getResponseHeaderValue(response, "Content-Type");

        Assert.assertEquals(actualStatusCode, 200, "Response status code is not 200");
        Assert.assertTrue(actualHeaderValue.contains("application/xml"), "Response 'Content-Type' header is not application/xml");
        Assert.assertTrue(actualStatusLine.contains("HTTP/1.1"), "The version of HTTP protocol is not 1.1");

        Assert.assertEquals(actualFirstName,expectedFirstName, "Expected and actual user first name do not match");
        Assert.assertEquals(actualLastName, expectedLastName, "Expected and actual user last name do not match");
        Assert.assertEquals(actualAddress, expectedAddress, "Expected and actual user address do not match");
        Assert.assertEquals(actualCity, expectedCity, "Expected and actual user city do not match");
        Assert.assertEquals(actualState, expectedState, "Expected and actual user state do not match");
        Assert.assertEquals(actualZipCode,expectedZipCode, "Expected and actual user zip code do not match");
        Assert.assertEquals(actualPhone, expectedPhone, "Expected and actual user phone number do not match");
        Assert.assertEquals(actualSsn, expectedSsn, "Expected and actual user ssn do not match");

        System.out.println("===================================================\n");
    }

    @Test(dataProvider = "InvalidCredentials", dataProviderClass = MyDataProvider.class)
    public void unsuccessfulLoginApiTest(String userName, String password) {
        url = loginApiUrl + userName + "/" + password;
        System.out.println("=================== Unsuccessful Login ===================");
        System.out.println("GET Request: " + url);

        //Call the API
        response = Requests.get(url);
        response.then().log().all();

        actualStatusCode = Requests.getResponseCode(response);
        actualStatusLine = Requests.getResponseStatusLine(response);
        actualHeaderValue = Requests.getResponseHeaderValue(response, "Content-Type");

        if (Objects.equals(userName, "")) {
            Assert.assertEquals(actualStatusCode, 400, "Response status code is not 400");
            Assert.assertTrue(response.asString().contains("Bad Request"), "Response reason phrase is not 'Bad Request'");
            Assert.assertTrue(actualHeaderValue.contains("text/html"), "Response 'Content-Type' header is not text/html");
        } else if (Objects.equals(password, "") || (password.contains(" "))) {
            Assert.assertEquals(actualStatusCode, 404, "Response status code is not 404");
            Assert.assertNull(actualHeaderValue, "Response 'Content-Type' header is not null");
        } else {
            Assert.assertEquals(actualStatusCode, 400, "Response status code is not 400");
            Assert.assertTrue(response.asString().equalsIgnoreCase("Invalid username and/or password"), "Response reason phrase is not 'Invalid username and/or password'");
            Assert.assertTrue(actualHeaderValue.contains("text/plain"), "Response 'Content-Type' header is not text/plain");
        }
        Assert.assertTrue(actualStatusLine.contains("HTTP/1.1"), "The version of HTTP protocol is not 1.1");

        System.out.println("===================================================\n");
    }
}