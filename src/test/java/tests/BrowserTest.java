package tests;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BrowserPage;
import org.junit.Test;
import utils.SearchResult;

import java.util.List;
import java.util.Map;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public class BrowserTest extends TestBase {
    Map<String, List<SearchResult>> map;


    @Test
    public void searchForKeywordAndShowResults() throws InterruptedException {
        BrowserPage browserPage = new BrowserPage(driver);
        driver.get(BASE_URL);
        browserPage.searchForKeywordOnChrome(keyword);
        driver.wait(5000);
        map = browserPage.getSearchResults(keyword);
        browserPage.printFirst10SearchResults(map);
    }

//    @AfterClass
//    public static void compareResultsFromAllBrowsers() {
//
//    }

}
