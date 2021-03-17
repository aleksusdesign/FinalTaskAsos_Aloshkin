package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//button[@data-test-id='add-button']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='_2XTT510 jVCq0Wv liqWkbK']")
    private WebElement addToCartPopupHeader;

    @FindBy(xpath = "//a[contains(text(),'Continue shopping')]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//a[contains(text(),'Continue to cart')]")
    private WebElement continueToCartButton;

    @FindBy(xpath = "//div[@class='product-hero']//h1")
    private WebElement titleOfProduct;

    @FindBy(xpath = "//span[@data-id='current-price']")
    private WebElement priceOfProduct;

    @FindBy(xpath = "//button[@class='star-rating-button']")
    private WebElement starRatingButton;

    @FindBy(xpath = "//a[@data-testid='miniBagIcon']")
    private WebElement cartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void clickCartButton() {
        cartButton.click();
    }

    public boolean isAddToCartButtonVisible() {
        return addToCartButton.isDisplayed();
    }

    public boolean isStarRatingButtonVisible() {
        return starRatingButton.isDisplayed();
    }

    public boolean isAddToCartPopupVisible() {
        return addToCartPopupHeader.isDisplayed();
    }

    public boolean isTitleOfProductVisible() {
        return titleOfProduct.isDisplayed();
    }

    public void isContinueShoppingButtonVisible() {
        continueShoppingButton.isDisplayed();
    }

    public boolean isPriceOfProductVisible() {
       return priceOfProduct.isDisplayed();
    }

    public String getAddToCartPopupHeaderText() {
        return addToCartPopupHeader.getText();
    }

    public void isContinueToCartButtonVisible() {
        continueToCartButton.isDisplayed();
    }

    public void clickContinueToCartButton() {
        continueToCartButton.click();
    }

    public WebElement getAddToCartPopupHeader() {
        return addToCartPopupHeader;
    }
}
