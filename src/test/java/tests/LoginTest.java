package tests;


import org.testng.Assert;
import pages.LoginPage;
import org.testng.annotations.Test;

import static utils.Constants.*;

public class LoginTest extends TestBase {

    @Test
    public void verifyLogin()  {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        loginPage.login();
        Assert.assertEquals(true, loginPage.findElementByText("Plans").isDisplayed());

    }


}
