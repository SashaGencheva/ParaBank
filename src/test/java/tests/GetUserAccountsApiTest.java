package tests;

import api.Requests;
import api.Converter;
import helpers.HooksApi;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.List;

public class GetUserAccountsApiTest extends HooksApi {
    String url;
    Response response;

    int expectedUserId;
    int actualUserId;
    List<String> accountIds;
    String actualSourceAccountId;
    int actualStatusCode;
    String actualStatusLine;
    String actualHeaderValue;

    @Test
    public void getUserAccountsApiTest(ITestContext context) {
        expectedUserId = (int) context.getSuite().getAttribute("userId");
        url = customerApiUrl + expectedUserId + accountApi;

        System.out.println("=================== Get User Accounts ===================");
        System.out.println("GET Request: " + url);

        //Call the API
        response = Requests.get(url);
        response.then().log().all();
        Converter.xmlToJson(response);

        response.then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("userAccountsXmlSchema.xsd"));

        accountIds = response.xmlPath().getList("accounts.account.id");
        System.out.println("Actual account IDs: " + accountIds);

        actualSourceAccountId = accountIds.get(0);
        System.out.println("Source account ID: " + actualSourceAccountId);

        //API Chaining
        context.getSuite().setAttribute("sourceAccountId", actualSourceAccountId);

        actualUserId = response.xmlPath().getInt("accounts.account.customerId");

        actualStatusCode = Requests.getResponseCode(response);
        actualStatusLine = Requests.getResponseStatusLine(response);
        actualHeaderValue = Requests.getResponseHeaderValue(response, "Content-Type");

        Assert.assertEquals(actualStatusCode, 200, "Response status code is not 200");
        Assert.assertTrue(actualHeaderValue.contains("application/xml"), "Response 'Content-Type' header is not application/xml");
        Assert.assertTrue(actualStatusLine.contains("HTTP/1.1"), "The version of HTTP protocol is not 1.1");

        Assert.assertEquals(actualUserId, expectedUserId, "Expected and actual user id do not match");

        System.out.println("===================================================\n");
    }
}