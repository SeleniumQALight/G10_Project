package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.PageProvider;
import utils.ConfigProvider;
import utils.ScreenShot;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import static utils.ConfigProvider.configProperties;

public class BaseTest {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    final String symbols = "-".repeat(10);
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();


    // цей блок виконується перед кожним тестом (аналог BeforeEach в JUnit 5)

    @Before
    public void setup() throws MalformedURLException {
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
//        webDriver.quit();
//        logger.info("Browser is closed");
        logger.info(symbols + testName.getMethodName() + " was finished " + symbols);
    }

    @Rule
    public TestName testName = new TestName();

    private WebDriver initDriver() throws MalformedURLException {
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
        }else if ("remote".equals(browserFromProperty)) {
            logger.info("Remote browser");
            // WebDriverManager.chromedriver().setup();
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName("chrome");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(cap);
            try {
                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        chromeOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else if ("remote-sauce".equals(browserFromProperty)) {
            logger.info("Remote browser");
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("Windows 11");
            browserOptions.setBrowserVersion("latest");
            HashMap<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("username", "oauth-radulenko-c446e");
            sauceOptions.put("accessKey", ConfigProvider.configHiddenProperties.saucelabs_pass());
            sauceOptions.put("build", "Taras-build1");
            sauceOptions.put("name", testName.getMethodName());
            browserOptions.setCapability("sauce:options", sauceOptions);

// start the session
            URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
            webDriver = new RemoteWebDriver(url, browserOptions);
        }

        else {
            throw new IllegalArgumentException("Browser " + browserFromProperty + " is not supported");
        }
        return webDriver;
    }

    @Rule()
    public final TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
            screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                    new ByteArrayInputStream(screenShot.getScreenShotImg())));
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }


        @Override
        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };

    protected void takeScreenshot(String screenShotName) {
        System.out.println("screenshot was taken");
        byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot(screenShotName, screen));
    }
}
