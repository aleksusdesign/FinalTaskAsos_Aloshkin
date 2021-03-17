package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SavedItemsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'itemCount')]")
    private WebElement countOfSavedItems;

    public SavedItemsPage(WebDriver driver) {
        super(driver);
    }

    public SavedItemsPage getSavedItemsPage() {
        return new SavedItemsPage(driver);
    }

    public WebElement getCountTitleOfSavedItems(){return countOfSavedItems;}

    public String getCountOfSavedItems(){return countOfSavedItems.getText().split(" ")[0];}
}
