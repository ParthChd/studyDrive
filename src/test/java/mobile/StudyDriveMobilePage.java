package mobile;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static mobile.MobileTestBase.*;

public class StudyDriveMobilePage {
    private final AndroidDriver driver;
    private static final Logger log = Logger.getLogger(StudyDriveMobilePage.class.getName());

    public StudyDriveMobilePage(AndroidDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @AndroidFindBy(id = "de.veedapp.veed.login_registration:id/button_facebook_login")
    private WebElement buttonFacebook;
    @AndroidFindBy(id = "de.veedapp.veed.login_registration:id/button_email_login")
    private WebElement buttonContinueWithEmail;
    @AndroidFindBy(id = "de.veedapp.veed.login_registration:id/button_google_login")
    private WebElement buttonGoogleLogin;
    @AndroidFindBy(id = "de.veedapp.veed:id/textInputEditText")
    private WebElement textBoxEmail;
    @AndroidFindBy(id = "de.veedapp.veed:id/textViewButtonIcon")
    private WebElement buttonContinue;
    @AndroidFindBy(id = "de.veedapp.veed:id/textInputEditText")
    private WebElement searchBox;
    @AndroidFindBy(id = "de.veedapp.veed.community_content:id/editTextComposeQuestion")
    private WebElement textBoxQuestion;
    @AndroidFindBy(id = "de.veedapp.veed.community_content:id/imageButtonSend")
    private WebElement buttonSendQuestion;
    @AndroidFindBy(id = "de.veedapp.veed.community_content:id/answerCountImageView")
    private WebElement buttonCommentIcon;
    @AndroidFindBy(id = "de.veedapp.veed.community_content:id/scrollCardViewContainer")
    private WebElement textBoxComment;
    @AndroidFindBy(id = "de.veedapp.veed.community_content:id/imageButtonSend")
    private WebElement buttonSendComment;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.EditText")
    private WebElement textBoxPassword;
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private WebElement buttonAllowNotification;
    @AndroidFindBy(id = "de.veedapp.veed:id/acceptAllLoadingButtonView")
    private WebElement buttonAcceptAll;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.ImageView")
    private WebElement selectModule;
    @AndroidFindBy(id = "de.veedapp.veed:id/actionButtonIcon")
    private WebElement buttonPlusIcon;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[2]/android.view.ViewGroup")
    private WebElement acceptPopup;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[1]/android.view.ViewGroup")
    private WebElement buttonDoYouLikeIt;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[3]/android.view.ViewGroup/android.view.ViewGroup")
    private WebElement buttonRateApp;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.ScrollView/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView")
    private WebElement valueComment;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.ImageView[2]")
    private WebElement selectQuestionOption;

    public String getConfigData(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    private final String email = getConfigData("email");
    private final String password = getConfigData("password");
    private final String moduleName = getConfigData("module_name");

    public boolean verifyElementOnboardingScreen() {
        return buttonFacebook.isDisplayed() && buttonContinueWithEmail.isDisplayed() && buttonGoogleLogin.isDisplayed();
    }

    public void acceptTermsAndConditions() {
        implicitlyWait();
        clickElement(buttonAllowNotification);
        log.info("✅ Allowed notifications");
        clickElement(buttonAcceptAll);
        log.info("✅ Accepted Terms & Conditions");
    }

    public void completeOnboadingProcess() {
        SoftAssert softAssert = new SoftAssert();

        clickElement(buttonContinueWithEmail);
        enterText(textBoxEmail, email);
        clickElement(buttonContinue);

        clickElement(textBoxPassword);
        enterText(textBoxPassword, password);

        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().resourceId(\"de.veedapp.veed.login_registration:id/loadingButton\"))"
        )).click();

        WebElement title = driver.findElement(By.id("de.veedapp.veed:id/headerTextView"));
        softAssert.assertEquals(title.getText(), "Hey, parth", "✅ User successfully logged in");
        softAssert.assertAll();
    }

    public void searchModule() {
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(
                MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().resourceId(\"de.veedapp.veed:id/textInputEditText\"))"
                )
        ).click();
        searchBox.sendKeys(moduleName);
        driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
        driver.pressKey(new KeyEvent().withKey(AndroidKey.SEARCH));

        log.info("✅ Successfully searched module");
        softAssert.assertAll();
    }

    public void selectModule() {
        SoftAssert softAssert = new SoftAssert();
        WebElement title = driver.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView"));
        softAssert.assertEquals(title.getText(), "Modules", "Verified Modules are section is properly visible");
        clickElement(selectModule);
        clickElement(buttonPlusIcon);
        clickElement(selectQuestionOption);
        clickElement(acceptPopup);
        enterText(textBoxQuestion, "Testing - " + generateRandomString(6));
        clickElement(buttonSendQuestion);
        clickElement(buttonDoYouLikeIt);
        clickElement(buttonRateApp);

        log.info("✅ Successfully selected module and posted question");
        softAssert.assertAll();
    }

    public void commentOnPost() {
        SoftAssert softAssert = new SoftAssert();

        clickElement(buttonCommentIcon);
        String commentBefore = valueComment.getText();
        clickElement(textBoxComment);
        driver.executeScript("mobile: shell", ImmutableMap.of("command", "input text 'Testing is fun'"));
        clickElement(buttonSendComment);
        String commentAfter = valueComment.getText();

        softAssert.assertNotEquals(commentBefore, commentAfter, "✅ Comment posted successfully");
        softAssert.assertAll();
    }
}
