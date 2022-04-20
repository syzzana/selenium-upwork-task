package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.BeforeSuite;
import org.junit.BeforeClass;
import org.junit.AfterClass;
//import org.junit.BeforeSuite;

abstract class TestBase {

    protected WebDriver driver;
    @BeforeClass
    public static void setupSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
     }

    @After
    public void teardown() {
        driver.quit();
    }

}
