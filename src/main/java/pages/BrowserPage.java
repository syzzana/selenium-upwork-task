package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowserPage extends AbstractPage {

    @FindBy(css = "[name='q']")
    private WebElement googleSearchBar;

    @FindBy(css = "[aria-label='Search with Google or enter address']")
    private WebElement firefoxSearchBar;

    public BrowserPage(WebDriver driver) {
        super(driver);
    }

    public void searchForKeywordOnChrome(String keyword) {
        waitForElementVisibility(googleSearchBar, 10);
        googleSearchBar.sendKeys(keyword);
        googleSearchBar.sendKeys(Keys.ENTER);
    }

    public void searchForKeywordOnFirefox(String keyword) {
        waitForElementVisibility(firefoxSearchBar, 30);
        firefoxSearchBar.sendKeys(keyword);
        firefoxSearchBar.sendKeys(Keys.ENTER);
    }
}
