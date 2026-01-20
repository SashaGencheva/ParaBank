package helpers;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HooksApi {
    protected static Properties prop;
    protected static String baseApi;
    protected static String loginApi;
    protected static String loginApiUrl;
    protected static String customerApi;
    protected static String customerApiUrl;
    protected static String accountApi;
    protected static String payBillApi;
    protected static String payBillApiUrl;

    @BeforeTest
    public void setUp() {
        try {
            prop = new Properties();
            String sysPath = System.getProperty("user.dir");

            //Calling the config.properties file
            FileInputStream inStream = new FileInputStream(sysPath + "/src/test/java/config/config.properties");

            //Loading the properties file
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseApi = prop.getProperty("baseApiUrl");
        loginApi = prop.getProperty("loginApiUrl");
        customerApi = prop.getProperty("customersApiUrl");
        accountApi = prop.getProperty("accountsApiUrl");
        payBillApi = prop.getProperty("billPayApiUrl");
        loginApiUrl = baseApi + loginApi;
        customerApiUrl = baseApi + customerApi;
        payBillApiUrl = baseApi + payBillApi;
    }
}