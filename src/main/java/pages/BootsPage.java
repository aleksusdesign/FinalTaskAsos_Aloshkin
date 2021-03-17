package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BootsPage extends BasePage {

    @FindBy(xpath = "//li[@data-dropdown-id='currentprice']//button[@class = '_1om7l06']")
    private WebElement rangeOfPriceButton;

    @FindBy(xpath = "//div[@class='_2EAcS_V _2H7teJE']")
    private WebElement rangeOfPricePopup;

    @FindBy(xpath = "//div[@data-testid='minIndicator']")
    private WebElement minIndicator;

    @FindBy(xpath = "//span[@data-testid='minValueLabel']")
    private WebElement minValueLabel;

    @FindBy(xpath = "//span[@data-testid='maxValueLabel']")
    private WebElement maxValueLabel;

    @FindBy(xpath = "//button[@aria-haspopup='listbox']")
    private WebElement sortButton;

    @FindBy(xpath = "//li[@id='plp_web_sort_price_low_to_high']")
    private WebElement lowToHighButton;

    @FindBy(xpath = "//label[@for= 'brand_53']")
    private WebElement asosLabel;

    @FindBy(xpath = "//li[@data-dropdown-id='brand']//button")
    private WebElement brandsSortButton;

    @FindBy(xpath = "//label[@for= 'brand_12311']")
    private WebElement baseLondonLabel;

    @FindBy(xpath = "//span[@class='_16nzq18']")
    private List<WebElement> pricesList;

    @FindBy(xpath = "//div[@data-auto-id='productTileDescription']//p")
    private List<WebElement> descriptionsList;

    public BootsPage(WebDriver driver) {
        super(driver);
    }

    public int getMinPrice(){return Integer.parseInt(minValueLabel.getText().substring(1));}

    public int getMaxPrice(){return Integer.parseInt(maxValueLabel.getText().substring(1));}

    public WebElement getRangeOfPriceButton() {
        return rangeOfPriceButton;
    }

    public WebElement getPriceOfFirstElement() {
        return pricesList.get(0);
    }

    public boolean isAsosLabelVisible() {
        return asosLabel.isDisplayed();
    }

    public boolean isBrandsSortButtonVisible() {
        return brandsSortButton.isDisplayed();
    }

    public void clickBrandsSortButton() {
        brandsSortButton.click();
    }

    public boolean isBaseLondonLabelVisible() {
        return baseLondonLabel.isDisplayed();
    }

    public void clickAsosLabel() {
        asosLabel.click();
    }

    public void clickBaseLondonLabel() {
        baseLondonLabel.click();
    }

    public WebElement getLowToHighButton() {
        return lowToHighButton;
    }

    public boolean isLowToHighButtonVisible(){return lowToHighButton.isDisplayed();}

    public boolean isSortButtonVisible(){return sortButton.isDisplayed();}

    public void clickLowToHighButton() {
        lowToHighButton.click();
    }

    public WebElement getSortButton() {
        return sortButton;
    }

    public void clickSortButton() {
        sortButton.click();
    }


    public void clickRangeOfPriceButton() {
        rangeOfPriceButton.click();
    }

    public WebElement getRangeOfPricePopup() {
        return rangeOfPricePopup;
    }

    public WebElement getMinIndicator() {
        return minIndicator;
    }

    public List<Float> getPricesList() {
        List<Float> list = new ArrayList<>();
        for(WebElement webElement : pricesList)
        {
            list.add(Float.parseFloat(webElement.getText().substring(1)));
        }
        return list;
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
