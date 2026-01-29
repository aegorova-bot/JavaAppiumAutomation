package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject{

    protected static String
        FOLDER_BY_NAME,
        ARTICLE_BY_TITLE,
        SYNC_YOUR_SAVED_ARTICLES,
        POP_UP_CLOSE_BUTTON,
        BASKET_ICON;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE.replace("{TITLE}", article_title);
    }

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder )
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick((folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                15);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath  = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent((article_xpath),
                "Cannot find saved article by title " + article_title,
                15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        String article_xpath  = getSavedArticleXpathByTitle(article_title);
        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft((article_xpath),
                "Cannot find saved article for to delete");
        if(Platform.getInstance().isIos())
        {
            this.waitForElementPresent(BASKET_ICON,
                    "Cannot find basket icon",
                    15);
            this.waitForElementAndClick(BASKET_ICON,
                    "Cannot find basket icon to delete article",
                    15);
        }
        this.waitForArticleToDisappearByTitle(article_title);

    }
    public void waitForArticleToDisappearByTitle(String article_title){

        String article_xpath  = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent((article_xpath),
                "Saved article with title " + article_title + " still presents",
                15);
    }

    public void closePopUp()
    {
        this.waitForElementPresent(SYNC_YOUR_SAVED_ARTICLES,
                "Cannot find pop-up 'Sync your saved articles?' ",
                15);
        this.waitForElementAndClick(POP_UP_CLOSE_BUTTON,
                "Cannot find close button on pop-up 'Sync your saved articles?'",
                15);
    }

    }

