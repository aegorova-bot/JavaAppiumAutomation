import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/aleksandraegorova/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia_50467_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After

    public void tearDown() {
        driver.quit();
    }

//    @Test
//
//    public void firstTest() {
//        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
//        element_skip.click();
//
//        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find 'Search Wikipedia' input",
//                5);
//
//        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//        waitForElementPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
//                "Cannot find 'Object-oriented programming language' searching by 'Java'",
//                15);
//    }
//
    @Test
    public void testCanselSearch() {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
                );
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find back button",
                5);
        waitForElementNotPresent(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Button still presents",
                5);

    }

    @Test
    public void testCompareArticleTitle()
    {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                15);

        WebElement title_element = waitForElementPresent(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find article title",
                15);
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals("We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test

    public void testSearchFieldContainsText()
    {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Search field does not contain expected text"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement assertElementHasText(By by, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String actual_text = element.getText();

        Assert.assertTrue(
                error_message,
                actual_text.contains(expected_text)
        );

        return element;
    }


}
