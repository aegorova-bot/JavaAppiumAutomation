package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "ios_predicate:name == '{SUBSTRING}' AND label == '{SUBSTRING}' AND value == '{SUBSTRING}'";
        FOOTER_ELEMENT = "ios_predicate:name == \"View article in browser\" AND label == \"View article in browser\" AND value == \"View article in browser\"";
        SAVE_BUTTON = "id:Save for later";
        BACK_BUTTON = "id:Back";
        CANCEL_BUTTON = "ios_predicate:name == \"Cancel\" AND label == \"Cancel\" AND value == \"Cancel\"";
    }

    public IOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}

