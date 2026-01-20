package pages;

import helpers.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BasePage {
    WebDriver driver;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Custom page method to get text of an element
     *
     * @param element Method parameter of WebElement type
     * @return It returns the visible text of an element as a String
     */
    public String getTextFromElement(WebElement element) {
        Waits.customWait(driver, 10, "Visibility Of Element", element);
        return element.getText();
    }

    /**
     * Custom page method to set value to element
     *
     * @param element The first method parameter of WebElement type
     * @param text    The second method parameter of String type
     */
    public void setValueToElement(WebElement element, String text) {
        Waits.customWait(driver, 10, "Visibility Of Element", element);
        element.sendKeys(text);
        Assert.assertTrue(getAttributeFromElement(element, "value").contains(text), "Expected and actual input value do not match");
    }

    /**
     * Custom page method to get value from element
     *
     * @param element The first method parameter of WebElement type
     * @param attr    The second method parameter of String type which is a specified attribute on an element
     * @return It returns the value of a certain attribute of an element
     */
    public String getAttributeFromElement(WebElement element, String attr) {
        Waits.customWait(driver, 10, "Visibility Of Element", element);
        return element.getAttribute(attr);
    }

    /**
     * Custom page method to erase the contents from text boxes
     *
     * @param element Method parameter of WebElement type
     */
    public void clearElement(WebElement element) {
        Waits.customWait(driver, 10, "Element To Be Clickable", element);
        element.clear();
        Assert.assertTrue(getAttributeFromElement(element, "value").isBlank());
    }

    /**
     * Custom page method to click on element
     *
     * @param element Method parameter of WebElement type
     */
    public void clickOnElement(WebElement element) {
        Waits.customWait(driver, 10, "Element To Be Clickable", element);
        element.click();
    }
}