package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TabsMenuPage extends AbstractPage {

    public TabsMenuPage(WebDriver driver) {
        super(driver);
    }

    public void goThroughTab(String tab) {
        waitForElementWithTextVisibility(tab, 10);
        findElementByText(tab).click();
    }
}
