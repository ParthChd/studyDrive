package mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import utils.DriverManager;
import java.net.MalformedURLException;
import java.util.logging.Logger;

public class MobileTestBase {
    AndroidDriver drivers;
    public static final Logger log = Logger.getLogger(String.valueOf(MobileTestBase.class));

    @BeforeMethod
    public void initdriver() throws MalformedURLException {
        drivers = DriverManager.capabilities();
    }

    private static AppiumDriverLocalService service;

    @AfterMethod
    public void windup() {
        drivers.quit();
    }

    public static void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            log.severe("Failed to click on element: " + element + " due to: " + e.getMessage());
        }
    }

    public static void enterText(WebElement element, String value) {
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            log.severe("Failed to write on element: " + element + " due to: " + e.getMessage());
        }
    }

    public static String generateRandomString(int count) {
        String randomString = RandomStringUtils.randomAlphabetic(count);
        return randomString;
    }
}
