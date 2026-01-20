package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "xpath://*[contains(@text, '{SUBSTRING}')]",
            FOOTER_ELEMENT = "xpath://*[contains(@text, 'View article in browser')]",
            SAVE_BUTTON = "id:org.wikipedia:id/page_save",
            ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action",
            NAME_FIELD = "id:org.wikipedia:id/text_input",
            OK_BUTTON = "id:android:id/button1",
            BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            EXISTING_LIST = "id:org.wikipedia:id/item_title";

    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATE METHODS*/
    private static String getTitle(String substring)
    {
        return TITLE.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    public WebElement waitForTitleElement(String substring)
    {
        String title_xpath = getTitle(substring);
        return this.waitForElementPresent((title_xpath),
                "Cannot find article title on page " + substring,
                15);
    }

    public String getArticleTitle(String substring)
    {
        WebElement title_element = waitForTitleElement(substring);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement((FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick((SAVE_BUTTON),
                "Cannot find save button",
                15);

        this.waitForElementAndClick((ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                15);

        this.waitForElementAndSendKeys((NAME_FIELD),
                name_of_folder,
                "Cannot find field 'Name'",
                15);

        this.waitForElementAndClick((OK_BUTTON),
                "Cannot find 'OK' button",
                15);
    }

    public void addArticleToExistingList()
    {
        this.waitForElementAndClick((SAVE_BUTTON),
                "Cannot find save button",
                15);

        this.waitForElementAndClick((ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                15);

        this.waitForElementAndClick((EXISTING_LIST),
                "Cannot find 'Add to list' button",
                15);

    }

    public void goBackToMainScreen()
    {
        this.waitForElementAndClick((BACK_BUTTON),
                "Cannot find back button",
                5);

    }

    public void articleHasTitleAssert(String substring)
    {
        String title_xpath = getTitle(substring);
        this.assertElementPresent(
                (title_xpath),
                "Article title is not present on the page"
        );
    }
}
