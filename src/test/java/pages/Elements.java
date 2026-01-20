package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Elements {
    protected WebDriver driver;

    //Login elements
    @FindBy(name = "username")
    public WebElement userNameField;

    @FindBy(xpath = "//b[normalize-space() = 'Username']")
    public WebElement userNameLabel;

    @FindBy(css = "input[name = 'password']")
    public WebElement passwordField;

    @FindBy(xpath = "//b[normalize-space() = 'Password']")
    public WebElement passwordLabel;

    @FindBy(xpath = "//input[@type = 'submit']")
    public WebElement loginBtn;

    @FindBy(linkText = "Register")
    public WebElement registerLink;

    @FindBy(className = "title")
    public WebElement errorTitle;

    @FindBy(className = "error")
    public WebElement errorMsg;

    //Account Services elements
    @FindBy(xpath = "//*[@id = 'leftPanel']/ul/li[8]/a")
    public WebElement logoutBtn;

    //Registration elements
    @FindBy(id = "customer.firstName")
    public WebElement firstNameInput;

    @FindBy(name = "customer.lastName")
    public WebElement lastNameInput;

    @FindBy(xpath = "//*[@name = 'customer.address.street']")
    public WebElement addressInput;

    @FindBy(css = "*[name = 'customer.address.city']")
    public WebElement cityInput;

    @FindBy(xpath = "//input[@id = 'customer.address.state']")
    public WebElement stateInput;

    @FindBy(css = "input[id = 'customer.address.zipCode']")
    public WebElement zipCodeInput;

    @FindBy(id = "customer.phoneNumber")
    public WebElement phoneNumberInput;

    @FindBy(name = "customer.ssn")
    public WebElement ssnInput;

    @FindBy(xpath = "//*[@name = 'customer.username']")
    public WebElement userNameInput;

    @FindBy(css = "input[name = 'customer.password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@id='repeatedPassword']")
    public WebElement repeatedPasswordInput;

    @FindBy(css = "input[value = 'Register']")
    public WebElement registerBtn;

    @FindBy(id = "customer.firstName.errors")
    public WebElement firstNameErrorMsg;

    @FindBy(id = "customer.lastName.errors")
    public WebElement lastNameErrorMsg;

    @FindBy(id = "customer.address.street.errors")
    public WebElement addressErrorMsg;

    @FindBy(id = "customer.address.city.errors")
    public WebElement cityErrorMsg;

    @FindBy(id = "customer.address.state.errors")
    public WebElement stateErrorMsg;

    @FindBy(id = "customer.address.zipCode.errors")
    public WebElement zipCodeErrorMsg;

    @FindBy(id = "customer.ssn.errors")
    public WebElement ssnErrorMsg;

    @FindBy(id = "customer.username.errors")
    public WebElement usernameErrorMsg;

    @FindBy(id = "customer.password.errors")
    public WebElement passwordErrorMsg;

    @FindBy(id = "repeatedPassword.errors")
    public WebElement repeatedPasswordErrorMsg;

    //Left Panel elements
    @FindBy(css = "#leftPanel > .smallText")
    public WebElement leftPanelTitle;

    //Right Panel elements
    @FindBy(css = "#rightPanel > .title")
    public WebElement rightPanelTitle;

    @FindBy(xpath = "//*[@id = 'rightPanel']/p")
    public WebElement rightPanelMsg;

    public Elements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}