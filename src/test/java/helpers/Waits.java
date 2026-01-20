package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    public static void customWait(WebDriver driver, long timeout, String condition, WebElement element) {
        switch (condition) {
            case "Visibility Of Element":
                new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
                break;
            case "Element To Be Clickable":
                new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
                break;
        }
    }
}