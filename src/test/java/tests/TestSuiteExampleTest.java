package tests;


import pages.LoginPage;
import org.testng.annotations.Test;
import pages.TabsMenuPage;

import static utils.Constants.*;

public class TestSuiteExampleTest extends TestBase {
    private TabsMenuPage tabsMenuPage;


    @Test(priority = 0)
    public void authenticate() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        loginPage.login();
        tabsMenuPage = new TabsMenuPage(driver);
    }

    @Test(priority = 1)
    public void validateTabs() {
       //
    }
}
