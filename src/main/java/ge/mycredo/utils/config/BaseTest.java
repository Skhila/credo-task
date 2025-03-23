package ge.mycredo.utils.config;

import ge.mycredo.data.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public abstract class BaseTest {
    protected static WebDriverWait wait;
    protected static WebDriver driver;
    protected static SoftAssert softAssert;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUpConfig(@Optional(Config.DEFAULT_BROWSER) String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new Exception("Invalid browser name: " + browser);
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT));
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver = null;
            wait = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
