package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SearchResult;

import java.util.*;
import java.util.stream.Collectors;

public class BrowserPage extends AbstractPage {

    @FindBy(css = "[name='q']")
    private WebElement searchBar;
    @FindBy(css = "div.jtfYYd div.yuRUbf a")
    private By url;
    @FindBy(css = "div.jtfYYd div.yuRUbf a h3")
    private By title;
    @FindBy(css = "div.jtfYYd div.NJo7tc div.VwiC3b span")
    private By desc;


    public BrowserPage(WebDriver driver) {
        super(driver);
    }

    public void searchForKeywordOnChrome(String keyword) {
        waitForElementVisibility(searchBar, 10);
        searchBar.sendKeys(keyword);
        searchBar.sendKeys(Keys.ENTER);
    }

    public Map<String, List<SearchResult>> getSearchResults(String keyword) {
        Map<String, List<SearchResult>> searchResultsByKeyword = new HashMap<>();
        List<WebElement> list = driver.findElements(By.cssSelector("div.jtfYYd"));

        searchResultsByKeyword.put(keyword, parseSearchResults(list));
        return searchResultsByKeyword;
    }

    private List<SearchResult> parseSearchResults(List<WebElement> list) {
        return list.stream().map(webElement -> {
            WebElement url = webElement.findElement(this.url);
            WebElement title = webElement.findElement(this.title);
            WebElement desc = webElement.findElement(this.desc);
//            WebElement url = list.get(0).findElement(By.cssSelector("div.jtfYYd div.yuRUbf a"));
//            WebElement title = list.get(0).findElement(By.cssSelector("div.jtfYYd div.yuRUbf a h3"));
//            WebElement desc = list.get(0).findElement(By.cssSelector("div.jtfYYd div.NJo7tc div.VwiC3b span"));
            return SearchResult.builder()
                    .url(url.getText())
                    .title(title.getText())
                    .desc(desc.getText())
                    .build();
        }).collect(Collectors.toList());

    }

    public void printFirst10SearchResults(Map<String, List<SearchResult>> map) {
        map.forEach((key, value) -> System.out.println(key + ":" + value));
    }

}
