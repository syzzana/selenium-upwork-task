package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.BrowserPage;
import org.junit.Test;
import utils.SearchResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static utils.Constants.*;

@RunWith(Parameterized.class)
public class BrowserTest extends TestBase {
    Map<String, List<SearchResult>> map;
    Properties storeProperties = new Properties();
    Properties loadProperties = new Properties();


    @Test
    public void searchForKeywordAndShowResults() throws InterruptedException, IOException {
        BrowserPage browserPage = new BrowserPage(driver);
        driver.get(BASE_URL);
        browserPage.searchForKeywordOnChrome(keyword);
        driver.wait(5000);
        map = browserPage.getSearchResults(keyword);
        browserPage.printFirst10SearchResults(map);
        saveSearchResultsToFile();
    }

    @After
    public static void saveResults() {

    }

    private void saveSearchResultsToFile() throws IOException {
        for(Map.Entry<String, List<SearchResult>> entry: map.entrySet()){
            storeProperties.put(entry.getKey(), entry.getValue());
        }
        storeProperties.store(new FileOutputStream("data.properties"), null);

        Map<String, List<SearchResult>> mapFromFile = new HashMap<String, List<SearchResult>>();
        loadProperties.load(new FileInputStream("data.properties"));

        for(String key: loadProperties.stringPropertyNames()){
            mapFromFile.put(key, (List)loadProperties.get(key));
        }
    }

}
