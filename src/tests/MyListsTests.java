package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList()
    {
        this.skipPreview();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.goBackToMainScreen();
        ArticlePageObject.goBackToMainScreen();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSavedArticles();
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListDz()
    {
        this.skipPreview();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
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
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSavedArticles();
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        String article_title = ArticlePageObject.getArticleTitle("Python (programming language)");
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

}
