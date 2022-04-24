package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowserPage extends  AbstractPage{

    @FindBy(css = "[name='q']")
    private WebElement searchBar;
    @FindBy(css = "[aria-label='Google Search']")
    private WebElement googleSearch;


    public BrowserPage(WebDriver driver) {
        super(driver);
    }

   public void searchForKeyword(String keyword) {
        waitForElementVisibility(searchBar, 10);
        searchBar.sendKeys(keyword);
        searchBar.sendKeys(Keys.ENTER);
//        waitForElementVisibility(googleSearch, 5);
//        googleSearch.click();
   }
}
