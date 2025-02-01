package mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class DriverManager {
    public static AndroidDriver capabilities() throws MalformedURLException {
        AndroidDriver driver;
        DesiredCapabilities cap = new DesiredCapabilities();
        File appDir = new File("src");
        File app;
        app = new File(appDir, "studyDrive.apk");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "TestPhone");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), cap);
        return driver;
    }
}
