package tests;

import api.Converter;
import api.Requests;
import helpers.HooksApi;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class PayBillApiTest extends HooksApi {
    String url;
    HashMap<String, String> queryMap;
    String payLoad;
    Response response;

    int actualStatusCode;
    String actualStatusLine;
    String actualHeaderValue;
    String expectedSourceAccountId;
    String actualSourceAccountId;
    String actualAmount;
    String actualPayeeName;

    @Test
    public void payBillApiTest(ITestContext context) {
        expectedSourceAccountId = (String) context.getSuite().getAttribute("sourceAccountId");
        queryMap = new HashMap<>();
        queryMap.put("accountId", expectedSourceAccountId);
        queryMap.put("amount", "5");

        url = payBillApiUrl;

        payLoad = "{\n" +
                "  \"name\": \"Simeon Ivanov\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Pirin 11 Str\",\n" +
                "    \"city\": \"Sofia\",\n" +
                "    \"state\": \"BG\",\n" +
                "    \"zipCode\": \"1000\"\n" +
                "  },\n" +
                "  \"phoneNumber\": \"0888123456\",\n" +
                "  \"accountNumber\": 17799\n" +
                "}";

        System.out.println("=================== Pay Bill ===================");

        //Call the API
        response = Requests.post(url, queryMap, payLoad);
        response.then().log().all();
        Converter.xmlToJson(response);

        response.then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("payBillXmlSchema.xsd"));

        actualStatusCode = Requests.getResponseCode(response);
        actualStatusLine = Requests.getResponseStatusLine(response);
        actualHeaderValue = Requests.getResponseHeaderValue(response, "Content-Type");

        actualSourceAccountId = response.xmlPath().getString("billPayResult.accountId");
        actualAmount = response.xmlPath().getString("billPayResult.amount");
        actualPayeeName = response.xmlPath().getString("billPayResult.payeeName");

        Assert.assertEquals(actualStatusCode, 200, "Response status code is not 200");
        Assert.assertTrue(actualHeaderValue.contains("application/xml"), "Response 'Content-Type' header is not application/xml");
        Assert.assertTrue(actualStatusLine.contains("HTTP/1.1"), "The version of HTTP protocol is not 1.1");

        Assert.assertEquals(actualSourceAccountId, expectedSourceAccountId, "Expected and actual source account id do not match");
        Assert.assertEquals(actualAmount, "5", "Expected and actual amount do not match");
        Assert.assertEquals(actualPayeeName, "Simeon Ivanov", "Expected and actual payee name do not match");

        System.out.println("===================================================\n");
    }
}