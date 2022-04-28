package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BrowserPage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public class BrowserTest extends TestBase {

    List<WebElement> parsedElementsFirefox = new ArrayList<>();
    List<WebElement> parsedElementsChrome = new ArrayList<>();


    @Test
    public void parseSearchResults() {
        BrowserPage browserPage = new BrowserPage(driver);
        driver.get(BASE_URL);
        browserPage.searchForKeywordOnChrome(keyword);
//        driver.findElements(By.cssSelector(""));
    }


}
