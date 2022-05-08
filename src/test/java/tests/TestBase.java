package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;
import static utils.Constants.BASE_URL;

abstract class TestBase {

    protected WebDriver driver;
    @Parameter(1)
    public String platform;
    @Parameter(2)
    public String browserVersion;
    @Parameter(0)
    public String browser;
    @Parameter(3)
    public String keyword;
    //no need when we setup remote driver
//    @BeforeClass
//    public static void setupSuite() {
////        if(browser.equalsIgnoreCase("firefox")){
////            WebDriverManager.firefoxdriver().setup();
////        }
////        else if(browser.equalsIgnoreCase("chrome")){
//            WebDriverManager.chromedriver().setup();
////        }
//    }

    @Before
    public void setup() throws InterruptedException {
        if(browser.equalsIgnoreCase("chrome")) {
//            ChromeOptions options = new ChromeOptions();
//            options.setAcceptInsecureCerts(true);
//            options.addArguments("user-data-dir=USER_DATA_PATH");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(); //
//            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.wait(5000);
            driver.manage().deleteAllCookies();
            driver.navigate().to(BASE_URL);
        } else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.navigate().to(BASE_URL);
        }

//        createDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Rule
    public TestName testName = new TestName() {
        public String getMethodName() {
            return String.format("%s", super.getMethodName());
        }
    };

    private void createDriver() throws MalformedURLException {
        String sauceUsername = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        MutableCapabilities sauceOts = new MutableCapabilities();
        sauceOts.setCapability("username", sauceUsername);
        sauceOts.setCapability("accessKey", sauceAccessKey);
        sauceOts.setCapability("name", testName.getMethodName());
//        sauceOts.setCapability("build", build);
        sauceOts.setCapability("commandTimeout", "30");

        MutableCapabilities browserOptions = new MutableCapabilities();
        browserOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
        browserOptions.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        browserOptions.setCapability(CapabilityType.BROWSER_NAME, browser);
        browserOptions.setCapability("sauce:options", sauceOts);

        String sauceUrl = "https://onedemans.saucelabs.com/wd/hub";
        URL url = new URL(sauceUrl);
        driver = new RemoteWebDriver(url, browserOptions);
    }

    @Parameters(name = "{0}, {1}, {2}, {4}")
    public static Collection<Object> crossBrowserData() {
        return Arrays.asList(new Object[][]{
                {"chrome", "windows 11", "latest", "automation"},
                {"firefox", "windows 11", "latest", "automation"}
        });
    }

}
