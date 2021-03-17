package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@id='chrome-header']")
    private WebElement header;

    @FindBy(xpath = "//div[@id='chrome-footer']")
    private WebElement footer;

    @FindBy(xpath = "//span[@type='bagUnfilled']")
    private WebElement cartIcon;

    @FindBy(xpath = "//span[@type='accountUnfilled']")
    private WebElement myAccountButton;

    @FindBy(xpath = "//a[@data-testid='signin-link']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@data-testid='signup-link']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@data-testid='myaccount-dropdown']")
    private WebElement myAccountPopup;

    @FindBy(xpath = "//a[@data-testid='men-floor']")
    private WebElement menFloorButton;

    @FindBy(xpath = "//a[@data-testid='women-floor']")
    private WebElement womenFloorButton;

    @FindBy(xpath = "//button[contains(@data-id, '87a')]")
    private WebElement shoesButton;

    @FindBy(xpath = "//a[text() = 'Boots']")
    private List<WebElement> bootsButton;

    @FindBy(xpath = "//div[@class='_1FSGXKY _2FR6csi']")
    private WebElement preferencesPopup;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement countrySelector;

    @FindBy(xpath = "//select[@id='currency']")
    private WebElement currencySelector;

    @FindBy(xpath = "//button[@data-testid='save-country-button']")
    private WebElement savePreferencesButton;

    @FindBy(xpath = "//input[@data-testid='search-input']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@data-testid='search-button-inline']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'wishlist-button')]//div[contains(@class,'items-count')]")
    private WebElement wishListProductsCount;

    @FindBy(xpath = "//span[@type='heartUnfilled']")
    private WebElement savedItemsButton;

    @FindBy(xpath = "//div[@id='chrome-header']//button[@data-testid='country-selector-btn']")
    private WebElement countrySelectorButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void isHeaderVisible() {
        header.isDisplayed();
    }

    public void isFooterVisible() {
        footer.isDisplayed();
    }

    public void isCartIconVisible() {
        cartIcon.isDisplayed();
    }

    public boolean isMenFloorButtonVisible() {
        return menFloorButton.isDisplayed();
    }

    public void clickOnMenFloorButton() {
        menFloorButton.click();
    }

    public void clickShoesButton() {
        shoesButton.click();
    }

    public void clickBootsButton() {
        bootsButton.get(1).click();
    }

    public WebElement getBootsButton() {
        return bootsButton.get(1);
    }

    public boolean isWomenFloorButtonVisible() {
        return womenFloorButton.isDisplayed();
    }

    public boolean isCountrySelectorVisible() {
        return countrySelector.isDisplayed();
    }

    public boolean isCurrencySelectorVisible() {
        return currencySelector.isDisplayed();
    }

    public boolean isSavePreferencesButtonVisible() {
        return savePreferencesButton.isDisplayed();
    }

    public void selectCountry(String country) {
        Select countrySelect = new Select(countrySelector);
        countrySelect.selectByValue(country);
    }

    public WebElement getSignInButton() {
        return signInButton;
    }
    public void clickSignInButton() {
        signInButton.click();
    }

    public String getCountrySelectorButtonText() {
        return countrySelectorButton.getAttribute("aria-label");
    }

    public void isMyAccountButtonVisible() {
        myAccountButton.isDisplayed();
    }

    public void clickMyAccountButton() {
        myAccountButton.click();
    }

    public void clickSavePreferencesButton() {
        savePreferencesButton.click();
    }

    public WebElement getSavePreferencesButton() {
        return savePreferencesButton;
    }

    public void clickSavedItemsButton() { savedItemsButton.click(); }

    public boolean isSignUpButtonVisible() {
        return signUpButton.isDisplayed();
    }

    public boolean isSignInButtonVisible() {
        return signInButton.isDisplayed();
    }

    public boolean isCountrySelectorButtonVisible() {
        return countrySelectorButton.isDisplayed();
    }

    public boolean isMyAccountPopupVisible() {
        return myAccountPopup.isDisplayed();
    }

    public WebElement getMyAccountPopup() {
        return myAccountPopup;
    }

    public WebElement getPreferencesPopup() {
        return preferencesPopup;
    }

    public void isSearchFieldVisible() {
        searchField.isDisplayed();
    }

    public void clickCartButton() {
        cartIcon.click();
    }

    public void clickCountrySelectorButton() {
        countrySelectorButton.click();
    }


    public void enterTextToSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void scrollToHeader() {
        scrollToElement(header);
    }

}
