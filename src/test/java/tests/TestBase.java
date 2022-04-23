package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.BeforeClass;

import static utils.Constants.BASE_URL;

abstract class TestBase {

    protected WebDriver driver;
    @BeforeClass
    @Parameterized.Parameters( name = "browser")
    public static void setupSuite(String browser) {
        if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
        }
    }

    @Before
    @Parameterized.Parameters(name = "browser")
    public void setup(String browser) {
        if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.navigate().to(BASE_URL);
            driver.manage().deleteAllCookies();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.navigate().to(BASE_URL);
            driver.manage().deleteAllCookies();
        }
     }

    @After
    public void teardown() {
        driver.quit();
    }

}
