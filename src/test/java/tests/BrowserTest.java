package tests;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BrowserPage;
import org.junit.Test;
import utils.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public class BrowserTest extends TestBase {
    Map<String, List<SearchResult>> map = new HashMap<>();
    List<SearchResult> searchResults = new ArrayList<>();


    @Test
    public void searchForKeywordAndShowResults() throws InterruptedException {
        BrowserPage browserPage = new BrowserPage(driver);
        driver.get(BASE_URL);
        browserPage.searchForKeywordOnChrome(keyword);
        driver.wait(5000);
        browserPage.parseSearchResults(searchResults, map, keyword);
        browserPage.printFirst10SearchResults(map);
    }

//    @Test
//    @AfterClass
//    public static void compareResultsFromAllBrowsers() {
//
//    }


}
