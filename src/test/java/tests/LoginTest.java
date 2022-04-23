package tests;


import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import org.junit.Test;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public class LoginTest extends TestBase {

    @Parameterized.Parameters
    public static String [] browsers() {
        return new String [] {"firefox", "chrome"};
    }

    //this test should run 2 times
    @Test
    public void verifyLogin()  {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        loginPage.login();
        Assert.assertEquals(true, loginPage.findElementByText("Plans").isDisplayed());
    }


}
