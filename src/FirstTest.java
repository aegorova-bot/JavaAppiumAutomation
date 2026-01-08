import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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

    @Test
    public void testCanselSearchDz() {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Kotlin",
                "Cannot find search input",
                5
        );
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cannot find search results",
                5);

        assertMoreThanOneElement(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Search returned less than two articles"
        );

        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"),
                "Search results still present",
                5);

    }

    @Test
    public void testSwipeArticle()
    {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Automation for Apps')]"),
                "Cannot find 'Automation for Apps' searching by 'Appium'",
                15);

        waitForElementPresent(By.xpath("//*[contains(@text, 'Appium')]"),
                "Cannot find article title",
                15);

        swipeUpToFindElement(By.xpath("//*[contains(@text, 'View article in browser')]"),
        "Cannot find the end of the article",
        20);

    }

    @Test
    public void saveFirstArticleToMyList()
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

        waitForElementPresent(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find article title",
                15);

        waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cannot find save button",
                15);

        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'Add to list' button",
                15);

        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find field 'Name'",
                15);

        waitForElementAndClick(By.id("android:id/button1"),
                "Cannot find 'OK' button",
                15);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find back button",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find back button",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find 'Saved' button",
                15);

        waitForElementAndClick(By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Cannot find 'Learning programming' saved articles",
                15);

        waitForElementPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language'",
                15);

        swipeElementToLeft(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' for swiping to left");

        waitForElementNotPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot delete saved article",
                15);
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String search_line = "CJD Christophorusschul";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*";
        waitForElementPresent(By.xpath(search_result_locator),
                "Cannot find anythinng by the request " + search_line,
                15);

        int amount_of_search_results = getAmountOfElements
                (By.xpath(search_result_locator));

        Assert.assertTrue("We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String search_line = "kjshfkjdhfkshdf";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        waitForElementPresent(By.xpath(empty_result_label),
                "Cannot find empty result label by request " + search_line,
                15);

        assertElementNotPresent(By.xpath(search_result_locator),
                "We found some results by request " + search_line);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        WebElement element_skip = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_skip.click();

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String search_line = "Java";

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' searching by " + search_line,
                15);

        String title_before_rotation = waitForElementAndGetAttribute
                (By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find title of  article",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute
                (By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                        "text",
                        "Cannot find title of  article",
                        15);

        Assert.assertEquals("Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute
                (By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                        "text",
                        "Cannot find title of  article",
                        15);

        Assert.assertEquals("Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);

    }

    @Test
    public void testCheckSearchArticleInBackGround()
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
                "Cannot find 'Object-oriented programming language' searching by ",
                15);

        waitForElementPresent(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find article title",
                15);

        driver.runAppInBackground(2);

        waitForElementPresent(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find article title after returning from background",
                15);


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
    private void assertMoreThanOneElement(By by, String errorMessage)
    {
        List<WebElement> elements = driver.findElements(by);

        Assert.assertTrue(
                errorMessage,
                elements.size() > 1
        );
    }

    protected void swipeUp (int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);


        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while(driver.findElements(by).size() == 0)
        {
            if(already_swiped > max_swipes)
            {
                waitForElementPresent(by,
                        "Cannot find element by swiping up. \n" + error_message,
                        0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        };
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
       WebElement element =  waitForElementPresent(by,
               error_message,
               10);
       int left_x = element.getLocation().getX();
       int right_x = left_x + element.getSize().getWidth();
       int upper_y = element.getLocation().getY();
       int lower_y = upper_y + element.getSize().getHeight();
       int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    };

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0)
        {
             String default_message = "An element '" + by.toString() + "'supposed to be not present";
             throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
