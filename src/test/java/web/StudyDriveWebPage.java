package web;

import mobile.StudyDriveMobilePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Constants;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Logger;

public class StudyDriveWebPage extends WebTestBase {

    private static final Logger log = Logger.getLogger(StudyDriveWebPage.class.getName());


    @FindBy(css = "button[data-testid='top-bar-guest_btn-login']")
    WebElement buttonLogin;

    @FindBy(css = "button[data-testid='top-bar-guest_btn-signup'] div div")
    WebElement buttonSignUp;

    @FindBy(xpath = "//div[@data-fe-test='btn-label']/div[@class='w-full pr-2']")
    WebElement buttonContinue;

    @FindBy(id = "study-button-type-student")
    WebElement buttonStudy;

    @FindBy(id = "vs1__option-1")
    WebElement selectUniversity;

    @FindBy(xpath = "//li[@class='vs__dropdown-option'][4]")
    WebElement selectMajorOfStudy;

    @FindBy(xpath = "//input[@class='vs__search text-black-500']")
    WebElement inputStudyProgram;

    @FindBy(xpath = "//li[@class='vs__dropdown-option'][1]//div//span")
    WebElement selectStudyProgram;

    @FindBy(xpath = "//input[@placeholder='Select your starting semester']")
    WebElement inputStartingSemester;

    @FindBy(xpath = "//li[@class='vs__dropdown-option'][1]")
    WebElement selectSemester;

    @FindBy(xpath = "//button[@data-testid='profile-register-continue-button']/div")
    WebElement buttonLetsGo;

    @FindBy(css = "input[data-testid='auth-form-username-input']")
    WebElement textBoxEmail;

    @FindBy(css = "input[data-testid='auth-form-password-input']")
    WebElement textBoxPassword;

    @FindBy(css = "button[data-testid='auth-form_btn-register']")
    WebElement buttonRegisterEmailPage;

    @FindBy(css = "button[data-testid='auth-form_btn-login']")
    WebElement buttonLoginEmailPage;

    @FindBy(xpath = "//img[contains(@class, 'rounded-full')]")
    WebElement imgProfileAvatar;

    @FindBy(css = "span[class='align-middle']")
    WebElement textErrorMessage;

    @FindBy(xpath = "//ul//li[@class='first:rounded-t last:rounded-b']")
    List<WebElement> listOfOptionOnProfileButton;

    @FindBy(css = "i[class='icon icon-close text-white-100 h-5 w-5']")
    WebElement buttonCloseTour;

    @FindBy(css = "input[placeholder='Enter university name']")
    WebElement inputUniversty;

    @FindBy(css = "ul li span:not(.text-black-500.text-sm)")
    List<WebElement> listOfStudyOptions;

    @FindBy(xpath = "//div[@class='flex relative w-full md:w-auto']//div[@class='w-full']")
    WebElement buttonJoinModule;

    @FindBy(xpath = "//i[@class='btn-icon icon icon-add-file']")
    WebElement buttonUploadDocument;

    @FindBy(css = "i[class='btn-icon icon icon-upload']")
    WebElement selectDocumentUpload;

    @FindBy(css = "input[class='vs__search text-black-500']")
    WebElement searchDocModule;

    @FindBy(css = "ul li[class='inline tab-panel__tab']:nth-child(2)")
    WebElement selectDiscussionTab;

    @FindBy(css = "div[class='w-full'] textarea")
    WebElement textBoxAskAQuestion;

    @FindBy(css = "div[class='relative'] i[class='btn-icon icon icon-send']")
    WebElement buttonSendQuestion;

    @FindBy(css = "div[class*='text-red']")
    WebElement messageInvalidEmail;

    @FindBy(css = "a[data-testid='card-item']:nth-child(1)")
    WebElement selectModule;

    public StudyDriveWebPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAcceptCookies() throws InterruptedException {
        //Click on Accept button.
        clickOnShadowElement();
    }

    public void SigningUp() throws InterruptedException {
        createFluentWait(driver);
        clickOnElement(buttonSignUp);
        clickOnElement(textBoxEmail);
        String email = writeTextOnElementReturnValue(textBoxEmail, "parth" + generateRandomInteger() + "@outlook.com");
        clickOnElement(textBoxPassword);
        writeTextOnElement(textBoxPassword, Constants.password);
        createFluentWait(driver);
        clickOnElement(buttonRegisterEmailPage);
        log.info("✅ Successfully signed up user");
    }


    public void verifyNewUseOnboardingr() throws InterruptedException {
        Thread.sleep(3000); //Need to add this because long load time
        createFluentWait(driver);
        clickOnElement(buttonContinue);
        createFluentWait(driver);
        clickOnElement(buttonStudy);
        createFluentWait(driver);
        clickOnElement(inputUniversty);
        createFluentWait(driver);
        writeTextOnElement(inputUniversty, Constants.universityName);
        createFluentWait(driver);
        clickOnElement(selectUniversity);
        createFluentWait(driver);
        clickOnElement(selectMajorOfStudy);
        createFluentWait(driver);
        clickOnElement(inputStudyProgram);
        clickOnElement(selectStudyProgram);
        createFluentWait(driver);
        clickOnElement(inputStartingSemester);
        clickOnElement(selectSemester);
        clickOnElement(buttonLetsGo);
        log.info("✅ New user onboarded successful");
    }

    public void loginWithExistingUser() throws InterruptedException {
        Thread.sleep(3000); //Need to add this because long load time
        createFluentWait(driver);
        clickOnElement(buttonLogin);
        clickOnElement(textBoxEmail);
        writeTextOnElement(textBoxEmail, Constants.emailID);
        clickOnElement(textBoxPassword);
        writeTextOnElement(textBoxPassword, Constants.password);
        clickOnElement(buttonLoginEmailPage);
        log.info("✅ Login with existing user successful");
    }

    public void joinModuleAndPostComment() throws InterruptedException, AWTException {
        driver.navigate().refresh();
        createFluentWait(driver);
        clickOnElement(searchDocModule);
        writeTextOnElement(searchDocModule, Constants.moduleName);
        searchDocModule.sendKeys(Keys.RETURN);
        driver.navigate().refresh();
        waitForElementToBeClickable(driver, selectModule);
        clickOnElement(selectModule);
        try {
            if (buttonJoinModule.isDisplayed()) {
                buttonJoinModule.click();
            }
        } catch (NoSuchElementException e) {
        }
        try {
            if (textBoxEmail.isDisplayed()) {
                clickOnElement(textBoxEmail);
                writeTextOnElement(textBoxEmail, Constants.emailID);
                clickOnElement(textBoxPassword);
                writeTextOnElement(textBoxPassword, Constants.password);
                clickOnElement(buttonRegisterEmailPage);
            }
        } catch (NoSuchElementException e) {}
        clickOnElement(selectDiscussionTab);
        clickOnElement(textBoxAskAQuestion);
        writeTextOnElement(textBoxAskAQuestion, generateRandomString());
        clickOnElement(buttonSendQuestion);
        log.info("✅ Posted comment successful");
    }

    public void logoutUser(String text) throws InterruptedException {
        createFluentWait(driver);
        clickOnElement(imgProfileAvatar);
        //Need to add static wait because of page redirection.
        Thread.sleep(2000);
        getSpecficElementFromList(listOfOptionOnProfileButton, text);
        log.info("✅ User logout successful");
    }
}
