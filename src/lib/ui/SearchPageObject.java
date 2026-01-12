package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text, '{SUBSTRING}')]",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
        SEARCH_RESULT_LOCATOR = "//*[@resource-id='org.wikipedia:id/search_results_list']" +
                    "//org.wikipedia.views.SearchResultView";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                15);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON),
                "Search cancel button still present",
                15);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                15);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search init element",
                15);

        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                15);
    }


    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line,
                "Cannot find and type into search input",
                15);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring,
                15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                15);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anythinng by the request ",
                15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_LOCATOR),
                "We found some results");
    }
}
