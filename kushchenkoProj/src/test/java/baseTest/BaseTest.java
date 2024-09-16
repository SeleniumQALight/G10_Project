package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.PageProvider;

import java.time.Duration;

import static utils.ConfigProvider.configProperties;

public class BaseTest {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    final String symbols = "-".repeat(10);


    // цей блок виконується перед кожним тестом (аналог BeforeEach в JUnit 5)

    @Before
    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();
        logger.info(symbols + testName.getMethodName() + " was started " + symbols);
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configProperties.TIME_FOR_IMPLICIT_WAIT()));
        logger.info("Browser is opened");
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser is closed");
        logger.info(symbols + testName.getMethodName() + " was finished " + symbols);
    }

    @Rule
    public TestName testName = new TestName();

    private WebDriver initDriver() {
        String browserFromProperty = System.getProperty("browser");
        logger.info("Browser from property: " + browserFromProperty);
        if ((browserFromProperty == null) || (browserFromProperty.equalsIgnoreCase("chrome"))) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            logger.info("Chrome browser is started");
        } else if (browserFromProperty.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browserFromProperty.equalsIgnoreCase("safari")) {
            WebDriverManager.getInstance(SafariDriver.class).setup();
            webDriver = new SafariDriver();
        } else {
            throw new IllegalArgumentException("Browser " + browserFromProperty + " is not supported");
        }
        return webDriver;
    }
}
