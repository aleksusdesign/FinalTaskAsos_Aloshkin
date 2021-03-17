package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {

    @FindBy(xpath = "//div[@class='bag-secondary-content-wrapper']//p[@class ='bag-total-button-holder']//a")
    private WebElement checkoutButton;

    @FindBy(xpath = "//li[@class='bag-item-holder']")
    private WebElement shoppingCartItem;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public boolean isCheckoutButtonVisible() {
        return checkoutButton.isDisplayed();
    }

    public boolean isShoppingCartItemVisible() {
        return shoppingCartItem.isDisplayed();
    }

    public WebElement getShoppingCartItem() {
        return shoppingCartItem;
    }

}
