package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public ShoppingCartPage getShoppingCartPage() {
        return new ShoppingCartPage(driver);
    }

    public SearchResultsPage getSearchResultsPage() {
        return new SearchResultsPage(driver);
    }
    public BootsPage getBootsPage() {
        return new BootsPage(driver);
    }

    public LogInPage getLogInPage() {
        return new LogInPage(driver);
    }

    public ProductPage getProductPage() {
        return new ProductPage(driver);
    }

    public SavedItemsPage getSavedItemsPage() { return new SavedItemsPage(driver); }
}
