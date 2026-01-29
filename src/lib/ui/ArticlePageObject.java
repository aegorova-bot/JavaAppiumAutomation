package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            ADD_TO_LIST_BUTTON,
            NAME_FIELD,
            OK_BUTTON,
            BACK_BUTTON,
            CANCEL_BUTTON,
            EXISTING_LIST;

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
        if(Platform.getInstance().isAndroid())
        {
        return title_element.getAttribute("text");
        }
        else
        {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid())
        {
        this.swipeUpToFindElement((FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
        } else
        {
            this.SwipeUpTillElementAppear((FOOTER_ELEMENT),
                    "Cannot find the end of article",
                    40);
        }
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
        if(Platform.getInstance().isAndroid())
        {
            this.waitForElementAndClick((BACK_BUTTON),
                    "Cannot find back button",
                    15);
        } else
        {
        this.waitForElementAndClick((BACK_BUTTON),
                "Cannot find back button",
                5);

        this.waitForElementAndClick((CANCEL_BUTTON),
                "Cannot find back button",
                5);
        }

    }

    public void articleHasTitleAssert(String substring)
    {
        String title_xpath = getTitle(substring);
        this.assertElementPresent(
                (title_xpath),
                "Article title is not present on the page"
        );
    }

    public void getArticlesToMySaved()
    {
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find option to add article to reading list",
                15);

    }
}
