package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            SAVED_ARTICLES_BUTTON;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }


    public void clickSavedArticles()
    {
        this.waitForElementAndClick((SAVED_ARTICLES_BUTTON),
                "Cannot find 'Saved' button",
                15);
    }


}
