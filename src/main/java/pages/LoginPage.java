package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Constants.*;

public class LoginPage extends  AbstractPage{

    @FindBy(css = "[name='username']")
    private WebElement username;
    @FindBy(css="[name='password']")
    private WebElement password;

    @FindBy(xpath = "//*[contains(text(),'Login']")
    private WebElement login;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getUsername() {
        return username;
    }

    public void submit() {
        waitForElementVisibility(login,5);
        login.click();
    }

    public void fillEmailOrUsername(final String emailAddress) {
        waitForElementVisibility(username,5);
        username.sendKeys(emailAddress);
    }

    public void fillPassword(final String pass) {
        waitForElementVisibility(password,5);
        password.sendKeys(pass);
    }

    public void login(){
        fillEmailOrUsername(USERNAME);
        fillPassword(PASSWORD);
        submit();
    }
}
