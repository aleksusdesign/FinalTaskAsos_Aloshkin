package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//button[@data-auto-id='saveForLater']")
    private List<WebElement> wishListIcon;

    @FindBy(xpath = "//article[@class = 'feature']")
    private List<WebElement> articleWithZeroResultSearchList;

    @FindBy(xpath = "//div[@data-auto-id='productTileDescription']//p")
    private List<WebElement> descriptionsList;

    @FindBy(xpath = "//a[@class='_3TqU78D']")
    private List<WebElement> productsList;

    @FindBy(xpath = "//section[contains(@class, 'backgroundWrapper')]//h2[@class='grid-text__title ']")
    private WebElement searchWithZeroResultTitle;

    @FindBy(xpath = "//a[@data-auto-id='loadMoreProducts']")
    private WebElement loadMoreButton;

    @FindBy(xpath = "//a[@data-auto-id='loadPreviousProducts']")
    private WebElement loadPreviousButton;

    @FindBy(xpath = "//div[@aria-label = 'Filters']")
    private WebElement filtersRegion;

    @FindBy(xpath = "//section[contains(@class, 'mu-section')]")
    private WebElement shopNewInSection;

    @FindBy(xpath = "//p[@data-auto-id='styleCount']")
    private WebElement stylesCountLabel;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void clickWishListOnFirstProduct() {
        wishListIcon.get(0).click();
    }

    public void clickOnFirstProduct() {
        productsList.get(0).click();
    }

    public String getSearchWithZeroResultTitle() {
        return searchWithZeroResultTitle.getText();
    }

    public boolean isFiltersRegionVisible() {
        try {
            return filtersRegion.isDisplayed();
        }
        catch (org.openqa.selenium.NoSuchElementException e){
            return false;
        }
    }

    public boolean isShopNewInSectionVisible() {
        return shopNewInSection.isDisplayed();
    }

    public int getSizeOfArticleWithZeroResultSearchList() {
        return articleWithZeroResultSearchList.size();
    }

    public int getSizeOfProductsList() {
        return productsList.size();
    }

    public void clickLoadMoreButton() {
        loadMoreButton.click();
    }

    public boolean isLoadMoreButtonVisible() {
        return loadMoreButton.isDisplayed();
    }
    public void clickLoadPreviousButton() {
        loadPreviousButton.click();
    }

    public boolean isLoadPreviousButtonVisible() {
        return loadPreviousButton.isDisplayed();
    }

    public WebElement getLoadMoreButton() {
        return loadMoreButton;
    }

    public WebElement getStylesCountLabel() {
        return stylesCountLabel;
    }

    public List<String> getDescriptionsList() {
        List<String> list = new ArrayList<>();
        for(WebElement webElement : descriptionsList)
        {
            list.add(webElement.getText());
        }
        return list;
    }
}
