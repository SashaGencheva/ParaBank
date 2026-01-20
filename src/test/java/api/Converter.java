package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.XML;

public class Converter {
    public static void xmlToJson(Response response) {
        JSONObject jsonResponse = XML.toJSONObject(response.asString());
        System.out.println("Response JSON from API: " + jsonResponse);
    }
}