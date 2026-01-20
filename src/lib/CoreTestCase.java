package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723";

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformENV();
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    protected void skipPreview() {

        WebElement element_to_skip_preview = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        element_to_skip_preview.click();
    }

    private DesiredCapabilities getCapabilitiesByPlatformENV() throws Exception
    {String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "9");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/aleksandraegorova/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia_50467_apps.evozi.com.apk");
        } else if (platform.equals(PLATFORM_IOS))
        {
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15");
        capabilities.setCapability("platformVersion", "17.2");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/aleksandraegorova/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        } else
        {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }
}
