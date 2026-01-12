import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

//    Удалить после рефакторинга тестов с ДЗ
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }
//    Удалить после рефакторинга тестов с ДЗ

    @Test

    public void testSearchFieldContainsText()
    {
        this.skipPreview();

        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Search field does not contain expected text"
        );
    }

    @Test
    public void testCanselSearchDz() {
        this.skipPreview();

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Kotlin",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cannot find search results",
                5);

        MainPageObject.assertMoreThanOneElement(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Search returned less than two articles"
        );

        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        MainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"),
                "Search results still present",
                5);

    }

    @Test
    public void testSaveTwoArticlesToMyListDz()
    {
        this.skipPreview();

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                15);

        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find article Java title",
                15);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cannot find save button",
                15);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'Add to list' button",
                15);

        String name_of_folder = "Learning programming";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find field 'Name'",
                15);

        MainPageObject.waitForElementAndClick(By.id("android:id/button1"),
                "Cannot find 'OK' button",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find back button",
                5);

        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field to clear it",
                5
        );

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Python",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Python (programming language)')]"),
                "Cannot find 'Python (programming language)' searching by 'Python'",
                15);

        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text, 'Python (programming language)')]"),
                "Cannot find article Python title",
                15);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cannot find save button",
                15);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'Add to list' button",
                15);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                "Cannot find existing list button",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find back button",
                5);

        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find back button",
                5);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find 'Saved' button",
                15);

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Cannot find 'Learning programming' saved articles",
                15);

        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language'",
                15);

        MainPageObject.waitForElementPresent(By.xpath("//*[contains(@text, 'Python (programming language)')]"),
                "Cannot find 'Python (programming language)'",
                15);

        MainPageObject.swipeElementToLeft(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' for swiping to left");


        MainPageObject.waitForElementNotPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot delete saved article",
                15);

        String title_in_saved_articles = MainPageObject.waitForElementAndGetAttribute
                (By.xpath("//*[contains(@text, 'Python (programming language)')]"),
                        "text",
                        "Cannot find title of article by 'Python (programming language)'",
                        15);

        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Python (programming language)')]"),
                "Cannot find 'Python (programming language)' to click",
                15);

        String title_of_saved_article = MainPageObject.waitForElementAndGetAttribute
                (By.xpath("//*[contains(@text, 'Python (programming language)')]"),
                        "text",
                        "Cannot find title of article by 'Python (programming language)'",
                        15);

        assertEquals("Article titles does not equal each other",
                title_in_saved_articles,
                title_of_saved_article);


    }

    @Test
    public void testArticleHasTitle()
    {
        this.skipPreview();

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find article in search results",
                15
        );

        MainPageObject.assertElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Article title is not present on the page"
        );
    }




}
