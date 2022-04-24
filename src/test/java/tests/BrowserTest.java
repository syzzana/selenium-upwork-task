package tests;


import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BrowserPage;
import org.junit.Test;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public class BrowserTest extends TestBase {

    @Test
    public void verifyLogin()  {
        BrowserPage loginPage = new BrowserPage(driver);
        driver.get(BASE_URL);
        loginPage.login();
        Assert.assertEquals(true, loginPage.findElementByText("Plans").isDisplayed());
    }


}
