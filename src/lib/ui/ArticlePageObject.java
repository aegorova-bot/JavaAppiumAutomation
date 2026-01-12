package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "//*[contains(@text, '{SUBSTRING}')]",
            FOOTER_ELEMENT = "//*[contains(@text, 'View article in browser')]",
            SAVE_BUTTON = "org.wikipedia:id/page_save",
            ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
            NAME_FIELD = "org.wikipedia:id/text_input",
            OK_BUTTON = "android:id/button1",
            BACK_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";

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
        return this.waitForElementPresent(By.xpath(title_xpath),
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
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(By.id(SAVE_BUTTON),
                "Cannot find save button",
                15);

        this.waitForElementAndClick(By.id(ADD_TO_LIST_BUTTON),
                "Cannot find 'Add to list' button",
                15);

        this.waitForElementAndSendKeys(By.id(NAME_FIELD),
                name_of_folder,
                "Cannot find field 'Name'",
                15);

        this.waitForElementAndClick(By.id(OK_BUTTON),
                "Cannot find 'OK' button",
                15);
    }

    public void goBackToMainScreen()
    {
        this.waitForElementAndClick(By.xpath(BACK_BUTTON),
                "Cannot find back button",
                5);

        this.waitForElementAndClick(By.xpath(BACK_BUTTON),
                "Cannot find back button",
                5);
    }
}
