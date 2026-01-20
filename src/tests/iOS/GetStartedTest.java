package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForTheFreeEncyclopedia();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForSearchInNearlyLanguages();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForHelpMakeTheAppBetter();
        WelcomePageObject.clickGetStartedButton();
    }
}
