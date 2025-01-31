package web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.apache.commons.lang3.RandomStringUtils;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebTestBase {

    protected static WebDriver driver;
    protected FluentWait<WebDriver> waiter;

    public WebTestBase(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ********** Common Element Actions **********

    public void clickOnElement(WebElement webElement) {
        webElement.click();
    }

    public void writeTextOnElement(WebElement webElement, String value) {
        webElement.sendKeys(value);
    }

    public String writeTextOnElementReturnValue(WebElement webElement, String value) {
        webElement.sendKeys(value);
        return value;
    }

    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static FluentWait<WebDriver> createFluentWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(80))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        createFluentWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public String generateRandomInteger() {
        return RandomStringUtils.randomNumeric(2);
    }

    public String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public void clickOnShadowElement() {
        try {
            Thread.sleep(10000);
            WebElement shadowHost = driver.findElement(By.id("usercentrics-cmp-ui"));
            SearchContext shadowRoot = shadowHost.getShadowRoot();
            WebElement shadowButton = shadowRoot.findElement(By.cssSelector("button[id=accept]"));
            shadowButton.click();
        } catch (NoSuchElementException  | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyElementIsVisibleInDom(WebElement webElement) {
        boolean result = false;
        try {
            implicitWait();
            if (webElement.isDisplayed()) {
                result = true;
            }
        } catch (NoSuchElementException e) {
            result = false;
        }
        return result;
    }

    public void getSpecficElementFromList(java.util.List<WebElement> webElement, String textToMatch) throws InterruptedException { //exception not required to be thrown
        implicitWait();
        int elementsValue = webElement.size();
        for (int i = 0; i < elementsValue; i++) {
            String text = webElement.get(i).getText().trim();
            if (textToMatch.equalsIgnoreCase(text)) {
                webElement.get(i).click();
                break;
            } else {
            }
        }
    }
}
