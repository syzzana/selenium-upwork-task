package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TabsMenuPage;

import static utils.Constants.BASE_URL;

public class TabsMenuTest extends TestBase {

    private String [] tabs = new String [] {"Plans", "Keywords", "Parameters", "Executions", "Scheduler", "Grid", "Admin"};
    private String [] visibleElements = new String[] {"New plan", "New keyword", "New parameter", "Execution list", "New task", "Agents", "Add user"};

    @Test
    public void validateTabsMenu() {
        TabsMenuPage tabsMenuPage = new TabsMenuPage(driver);
        driver.get(BASE_URL);
        for(int i=0, j=0; i<tabs.length && j<visibleElements.length; i++, j++){
            tabsMenuPage.goThroughTab(tabs[i]);
            tabsMenuPage.waitForElementWithTextVisibility(visibleElements[j], 10);
            Assert.assertEquals(true, tabsMenuPage.findElementByText(visibleElements[j]).isDisplayed());
        }
    }
}
