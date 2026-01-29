package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }
        else
        {
            ArticlePageObject.getArticlesToMySaved();
        }

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.goBackToMainScreen();
            ArticlePageObject.goBackToMainScreen();
        }
        else
        {
            ArticlePageObject.goBackToMainScreen();
        }


        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickSavedArticles();
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid())
        {
            MyListPageObject.openFolderByName(name_of_folder);

        }
        else
        {
            MyListPageObject.closePopUp();
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListDz()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.goBackToMainScreen();
        SearchPageObject.waitForElementAndClear();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.clickByArticleWithSubstring("Python (programming language)");
        ArticlePageObject.waitForTitleElement("Python (programming language)");
        ArticlePageObject.addArticleToExistingList();
        ArticlePageObject.goBackToMainScreen();
        ArticlePageObject.goBackToMainScreen();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickSavedArticles();
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        String article_title = ArticlePageObject.getArticleTitle("Python (programming language)");
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

}
