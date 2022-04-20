package tests;


import org.junit.Assert;
import pages.LoginPage;
import org.junit.Test;

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
