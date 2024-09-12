package baseBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.PageProvider;

import java.time.Duration;

import static utils.ConfigProvider.configProperties;


public class BaseTest {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    final String symbols = "-".repeat(10);

    // цей блок виконується перед кожним тестом
    @Before
    public void setup(){
        logger.info(symbols + testName.getMethodName() + " was started " + symbols);
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configProperties.TIME_FOR_IMPLICIT_WAIT()));
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);

    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info(symbols + testName.getMethodName() + " was finished " + symbols);
    }

    @Rule
    public TestName testName = new TestName();


    private WebDriver initDriver() {
        String browserFromProperty = System.getProperty("browser");
        logger.info("Browser is " + browserFromProperty);
        if ((browserFromProperty == null) || (browserFromProperty.equalsIgnoreCase("chrome"))){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            logger.info("Browser is chrome");
        } else if (browserFromProperty.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("ie".equals(browserFromProperty.toLowerCase())){
            WebDriverManager.iedriver().setup(); //zoom 100%
            webDriver = new InternetExplorerDriver(); //security level - Medium
        } else if ("safari".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser " + browserFromProperty + " is not supported");
        }

        return webDriver;
    }
}
