package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Constants;

import javax.mail.MessagingException;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver");
        ChromeOptions cO = new ChromeOptions();
        cO.addArguments("--remote-allow-origins=*");
        cO.addArguments("disable-infobars");
        cO.addArguments("--disable-blink-features=AutomationControlled");
        cO.addArguments("--start-maximized");
        cO.addArguments("--start-fullscreen");
        //If you want to run on headless browser uncomment the below command.
        //cO.setHeadless(true);
        driver = new ChromeDriver(cO);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.url);
    }

    @AfterClass
    public void wrapUp() throws MessagingException {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}