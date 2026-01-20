package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
            SAVED_ARTICLES_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";

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
