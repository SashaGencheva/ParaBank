package runner;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        List<String> xmlFiles = new ArrayList<>();
        xmlFiles.add("./suiteWeb.xml");
        xmlFiles.add("./suiteApi.xml");

        TestNG testNG = new TestNG();
        testNG.setTestSuites(xmlFiles);
        testNG.run();
    }
}