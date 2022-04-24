package tests;


import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BrowserPage;
import org.junit.Test;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public class BrowserTest extends TestBase {

    @Test
    public void searchForKeyword()  {
        BrowserPage browserPage = new BrowserPage(driver);
        if(this.browser.equalsIgnoreCase("chrome")) {
            driver.get(GOOGLE_BASE_URL);
            browserPage.searchForKeyword(keyword);
        } else if(this.browser.equalsIgnoreCase("firefox")){
            driver.get(FIREFOX_BASE_URL);
        }
    }


}
