package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class AbstractPage {

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementVisibility(WebElement element, int durationInSec) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(durationInSec));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void  waitForElementToBeClickable(By path, int durationInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSec));
        wait.until(ExpectedConditions.elementToBeClickable(path));
    }

    public void waitForInvisibilityOfElementLocated(By element, int durationInSec){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSec));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public WebElement findElementByText(String text) {
       return driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
    }

    public void waitForElementWithTextVisibility(String text, int durationInSec) {
        waitForElementVisibility(findElementByText(text), durationInSec);
    }

}
