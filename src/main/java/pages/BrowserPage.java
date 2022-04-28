package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowserPage extends AbstractPage {

    @FindBy(css = "[name='q']")
    private WebElement searchBar;
    // /*//div/a[contains(@href,'automation')]
    // //div//h3[contains(.,'Automation')]
    public BrowserPage(WebDriver driver) {
        super(driver);
    }

    public void searchForKeywordOnChrome(String keyword) {
        waitForElementVisibility(searchBar, 10);
        searchBar.sendKeys(keyword);
        searchBar.sendKeys(Keys.ENTER);
    }

}
