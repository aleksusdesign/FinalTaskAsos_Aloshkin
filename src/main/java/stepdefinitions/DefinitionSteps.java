package stepdefinitions;


import utils.APIClient;
import utils.APIException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.*;
import utils.SlackMessage;
import utils.SlackUtils;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.*;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 60;
    WebDriver driver;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    SavedItemsPage savedItemsPage;
    LogInPage logInPage;
    BootsPage bootsPage;
    Actions action;
    APIClient testRail;
    PageFactoryManager pageFactoryManager;
    private int minPrice;
    private int maxPrice;
    private SlackMessage slackMessage;
    private static final String REGEX = "\\D+";
    private static final String EXPECTED_BRAND1 = "ASOS DESIGN";
    private static final String EXPECTED_BRAND2 = "Base London";

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        testRail = new APIClient("https://aleksus.testrail.com");
        testRail.setUser("aleksus20103@gmail.com");
        testRail.setPassword("12345");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Map data = new HashMap();
            data.put("status_id", 5);
            data.put("comment", "This test worked bad!");
            try {
                testRail.sendPost("add_result_for_case/T1/C1",data);
            } catch (APIException | IOException ioException) {
                ioException.printStackTrace();
            }
        }

        @Override
        protected void succeeded(Description description) {
            Map data = new HashMap();
            data.put("status_id", 1);
            data.put("comment", "This test worked fine!");
            try {
                testRail.sendPost("add_result_for_case/R1/T1",data);
            } catch (APIException | IOException ioException) {
                ioException.printStackTrace();
            }
        }
    };

    @Given("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @And("User checks header visibility")
    public void checkHeaderVisibility() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.isHeaderVisible();
    }

    @And("User checks search field visibility")
    public void checkSearchVisibility() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.isSearchFieldVisible();
    }

    @And("User checks footer visibility")
    public void checkFooterVisibility() {
        homePage.isFooterVisible();
    }

    @And("User checks cart visibility")
    public void checkCartVisibility() {
        homePage.isCartIconVisible();
    }

    @And("User checks that language switcher is {string}")
    public void checkLanguage(final String language) {
        assertTrue(homePage.getCountrySelectorButtonText().contains(language));
    }

    @And("User checks sign in button visibility")
    public void checkSignInButtonVisibility() {
        homePage.isSignInButtonVisible();
    }

    @When("User clicks 'My Account' button")
    public void clickMyAccountButton() {
        homePage.clickMyAccountButton();
    }

    @And("User checks my account popup visibility")
    public void checkMyAccountPopupVisibility() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getMyAccountPopup());
        assertTrue(homePage.isMyAccountPopupVisible());
    }

    @And("User checks 'Sign In' and 'Sign Up' buttons visibility on my account popup")
    public void checkSignInUpButtonsVisibility() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSignInButton());
        assertTrue(homePage.isSignInButtonVisible());
        assertTrue(homePage.isSignUpButtonVisible());
    }

    @When("User clicks country selector button")
    public void clickCountrySelectorButton() {
        homePage.clickCountrySelectorButton();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getPreferencesPopup());
    }



    @And("User makes search by keyword {string}")
    public void enterKeywordToSearchField(final String keyword) {
        homePage.enterTextToSearchField(keyword);
    }

    @And("User clicks search button")
    public void clickSearchButton() {
        homePage.clickSearchButton();
    }

    @And("User clicks 'Add to Cart' button on product")
    public void clickAddToCart() {
        productPage = pageFactoryManager.getProductPage();
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        productPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
        productPage.clickAddToCartButton();
    }

    @And("User checks that add to cart popup visible")
    public void checkAddToCartPopupVisibility() {
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getAddToCartPopupHeader());
        assertTrue(productPage.isAddToCartPopupVisible());
    }

    @And("User checks 'Continue Shopping' button visibility")
    public void checkContinueShoppingButtonVisibility() {
        productPage.isContinueShoppingButtonVisible();
    }

    @And("User checks 'Continue to Cart' button visibility")
    public void checkContinueToCartButtonVisibility() {
        productPage.isContinueToCartButtonVisible();
    }

    @And("User checks that add to cart popup header is {string}")
    public void checkAddToCartPopupHeader(final String expectedText) {
        assertEquals(productPage.getAddToCartPopupHeaderText(), expectedText);
    }

    @And("User clicks 'Continue to Cart' button")
    public void clickContinueToCartButton() {
        productPage.clickContinueToCartButton();
    }

    @And("User clicks 'Checkout' button")
    public void clickCheckoutButton() {
        shoppingCartPage = pageFactoryManager.getShoppingCartPage();
        shoppingCartPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, shoppingCartPage.getShoppingCartItem());
        shoppingCartPage.clickCheckoutButton();
    }


    @And("User clicks save on first product")
    public void clickSave() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.clickWishListOnFirstProduct();
    }

    @And("User checks that amount of products in saved list are {string}")
    public void checkAmountOfProductsInSavedList(final String expectedAmount) {
        savedItemsPage = pageFactoryManager.getSavedItemsPage();
        savedItemsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, savedItemsPage.getCountTitleOfSavedItems());
        assertEquals(savedItemsPage.getCountOfSavedItems(), expectedAmount);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @And("User clicks on the saved items button")
    public void userClicksOnTheSavedItemsButton() {
        homePage.scrollToHeader();
        homePage.clickSavedItemsButton();
    }

    @And("User checks country selector visibility")
    public void userChecksCountrySelectorVisibility() {
        assertTrue(homePage.isCountrySelectorButtonVisible());
    }

    @And("User checks country and currency select visibility")
    public void userChecksCountryAndCurrencySelectVisibility() {
        assertTrue(homePage.isCountrySelectorVisible());
        assertTrue(homePage.isCurrencySelectorVisible());
        assertTrue(homePage.isSavePreferencesButtonVisible());
    }


    @And("User select {string}")
    public void userSelectCountry(final String country) {
        homePage.selectCountry(country);
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSavePreferencesButton());
        homePage.clickSavePreferencesButton();
    }

    @And("User checks men and floor button visibility")
    public void userChecksMenAndFloorButtonVisibility() {
        assertTrue(homePage.isMenFloorButtonVisible());
        assertTrue(homePage.isWomenFloorButtonVisible());
    }

    @And("User clicks men floor button")
    public void userClicksMenFloorButton() {
        homePage.clickOnMenFloorButton();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User clicks shoes button")
    public void userClicksShoesButton() {
        homePage.clickShoesButton();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getBootsButton());
    }

    @And("User clicks boots button")
    public void userClicksBootsButton() {
        homePage.clickBootsButton();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User clicks price range button")
    public void userScrollToPriceRange() {
        bootsPage = pageFactoryManager.getBootsPage();
        bootsPage.scrollToElement(bootsPage.getRangeOfPriceButton());
        bootsPage.clickRangeOfPriceButton();
        bootsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, bootsPage.getRangeOfPricePopup());
    }

    @When("User drags 'Min price' indicator <{int}> points")
    public void userDragsMinPriceTo(final int offSet) {
        action = new Actions(driver);
        action.dragAndDropBy(bootsPage.getMinIndicator(), offSet,0).perform();
        bootsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        minPrice = bootsPage.getMinPrice();
        maxPrice = bootsPage.getMaxPrice();
    }

    @Then("User checks that prices of products in list are in range")
    public void userChecksThatPricesOfProductsInListAreInRange() {
        driver.navigate().refresh();
        bootsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (float price : bootsPage.getPricesList()) {
            assertTrue(price>=minPrice&&price<=maxPrice);
        }
    }

    @And("User checks sort button visible")
    public void userChecksSortButtonVisible() {
        bootsPage = pageFactoryManager.getBootsPage();
        assertTrue(bootsPage.isSortButtonVisible());
    }

    @And("User clicks sort button")
    public void userClicksSortButton() {
        bootsPage.clickSortButton();
    }

    @And("User checks low to high button visible")
    public void userChecksLowToHighButtonVisible() {
        assertTrue(bootsPage.isLowToHighButtonVisible());
    }

    @When("User clicks low to high button")
    public void userClicksLowToHighButton() {
        bootsPage.clickLowToHighButton();
        bootsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        driver.navigate().refresh();
        bootsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @Then("User checks that prices of products in list are low to high")
    public void userChecksThatPricesOfProductsInListAreLowToHigh() {
        List<Float> expected = bootsPage.getPricesList();
        Collections.sort(expected);
        assertEquals(expected, bootsPage.getPricesList());
    }

    @And("User checks brands button filter visible")
    public void userChecksBrandsButtonFilterVisible() {
        bootsPage = pageFactoryManager.getBootsPage();
        assertTrue(bootsPage.isBrandsSortButtonVisible());
    }

    @And("User clicks 'Brands Sort' button")
    public void userClicksBrandsSortButton() {
        bootsPage.clickBrandsSortButton();
    }

    @And("User checks 'Asos' and 'Base London' labels are visible")
    public void userChecksAsosAndBaseLondonLabelsAreVisible() {
        assertTrue(bootsPage.isAsosLabelVisible());
        assertTrue(bootsPage.isBaseLondonLabelVisible());
    }

    @When("User clicks 'Asos' and 'Base London' labels")
    public void userClicksAsosAndBaseLondonLabels() {
        bootsPage.clickAsosLabel();
        driver.navigate().refresh();
        bootsPage.clickBrandsSortButton();
        bootsPage.clickBaseLondonLabel();
        driver.navigate().refresh();

    }

    @Then("User checks that descriptions of products contains brand`s name")
    public void userChecksThatDescriptionsOfProductsContainsBrandSName() {
        for (String s : bootsPage.getDescriptionsList()) {
            assertTrue(s.contains(EXPECTED_BRAND1) || s.contains(EXPECTED_BRAND2));
        }
    }

    @And("User checks that text of title are {string}")
    public void userChecksThatTextOfTitleAreTextOfTitle(final String textOfTitle) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(searchResultsPage.getSearchWithZeroResultTitle().contains(textOfTitle));
    }

    @And("User checks that 'Filters region' are invisible")
    public void userChecksThatFiltersRegionAreInvisible() {
        assertFalse(searchResultsPage.isFiltersRegionVisible());
    }

    @And("User checks that 'Shop New in' section are visible")
    public void userChecksThatShopNewInSectionAreVisible() {
        assertTrue(searchResultsPage.isShopNewInSectionVisible());
    }

    @Then("User checks that 'Shop New in' section consist <{int}> articles")
    public void userChecksThatShopNewInSectionConsistArticles(final int expectedCount) {
        assertEquals(expectedCount,searchResultsPage.getSizeOfArticleWithZeroResultSearchList());
    }


    @And("User checks that amount of products is <{int}>")
    public void userChecksThatAmountOfProductsIs(final int expectedAmount) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(expectedAmount, searchResultsPage.getSizeOfProductsList());
    }

    @And("User clicks 'Load More' button")
    public void userClicksLoadMoreButton() {
        searchResultsPage.clickLoadMoreButton();
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getLoadMoreButton());
    }

    @When("User reload a page")
    public void userReloadAPage() {
        driver.navigate().refresh();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @Then("User checks that current url contains {string}")
    public void userChecksThatCurrentUrlContainsPage(final String str) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(driver.getCurrentUrl().contains(str.toLowerCase()));
    }

    @And("User checks that 'Load More' button visibility")
    public void userChecksThatLoadMoreButtonVisibility() {
        assertTrue(searchResultsPage.isLoadMoreButtonVisible());
    }

    @And("User checks that 'Load Previous' button visibility")
    public void userChecksThatLoadPreviousButtonVisibility() {
        assertTrue(searchResultsPage.isLoadPreviousButtonVisible());
    }

    @And("User clicks 'Load Previous' button")
    public void userClicksLoadPreviousButton() {
        searchResultsPage.clickLoadPreviousButton();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getStylesCountLabel());
    }

    @And("User clicks on the first product")
    public void userClicksOnTheFirstProduct() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.clickOnFirstProduct();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User checks that title of product is visible")
    public void userChecksThatTitleOfProductIsVisible() {
        productPage = pageFactoryManager.getProductPage();
        assertTrue(productPage.isTitleOfProductVisible());
    }

    @And("User checks that 'Add to Cart' button is visible")
    public void userChecksThatAddToCartButtonIsVisible() {
        assertTrue(productPage.isAddToCartButtonVisible());
    }

    @And("User checks that price is visible")
    public void userChecksThatPriceIsVisible() {
        assertTrue(productPage.isPriceOfProductVisible());
    }

    @And("User checks that 'Star Rating' button is visible")
    public void userChecksThatStarRatingButtonIsVisible() {
        assertTrue(productPage.isPriceOfProductVisible());
    }

    @And("User clicks 'Cart' button")
    public void userClicksCartButton() {
        productPage.clickCartButton();
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User checks 'Checkout' button visibility")
    public void userChecksCheckoutButtonVisibility() {
        assertTrue(shoppingCartPage.isCheckoutButtonVisible());
    }

    @And("User checks item in shopping cart visibility")
    public void userChecksItemInShoppingCartVisibility() {
        shoppingCartPage = pageFactoryManager.getShoppingCartPage();
        shoppingCartPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, shoppingCartPage.getShoppingCartItem());
        assertTrue(shoppingCartPage.isShoppingCartItemVisible());
    }

    @And("User clicks 'Sign In' popup button")
    public void userClicksSignInButton() {
        homePage.clickSignInButton();
    }

    @And("User checks that email and password inputs are visible")
    public void userChecksThatEmailAndPasswordInputsAreVisible() {
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(logInPage.isEmailInputVisible());
        assertTrue(logInPage.isPasswordInputVisible());
        assertTrue(logInPage.isSignInButtonVisible());
    }

    @And("User insert {string} and {string}")
    public void userInsertEmailAndPassword(final String email, final String password) {
        logInPage.insertEmailInput(email);
        logInPage.insertPasswordInput(password);
    }

    @And("User checks that email error is visible")
    public void userChecksThatEmailErrorIsVisible() {
        assertTrue(logInPage.isEmailErrorVisible());
    }

    @And("User clicks 'Sign In' button")
    public void userClicksLogSignInButton() {
        logInPage.clickSignInButton();
    }

    @And("User checks that password error is visible")
    public void userChecksThatPasswordErrorIsVisible() {
        assertTrue(logInPage.isPasswordErrorVisible());
    }

    @Then("User checks that login error is visible")
    public void userChecksThatLoginErrorIsVisible() {
        assertTrue(logInPage.isLogInErrorVisible());
    }

    @Then("User checks that results contains {string}")
    public void userChecksThatResultsContainsKeyword(final String keyword) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (String s : searchResultsPage.getDescriptionsList()) {
            assertTrue(s.toLowerCase().contains(keyword));
        }
    }

    @After
    public void afterMethod(Scenario scenario) {
        if(scenario.isFailed()) {
            Map data = new HashMap();
            data.put("status_id", 5);
            data.put("comment", "This test worked bad!");
            try {
                testRail.sendPost("add_result_for_case/1/" + getCaseNumber(scenario), data);
            } catch (APIException | IOException ioException) {
                ioException.printStackTrace();
            }
            slackMessage = new SlackMessage("Oleksii", "Failed: " + scenario.getName(), ":red_circle:");
            SlackUtils.sendMessage(slackMessage);
        }
        else {
            Map data = new HashMap();
            data.put("status_id", 1);
            data.put("comment", "This test worked fine!");
            try {
                testRail.sendPost("add_result_for_case/1/" + getCaseNumber(scenario), data);
            } catch (APIException | IOException ioException) {
                ioException.printStackTrace();
            }
            slackMessage = new SlackMessage("Oleksii", "Passed: " + scenario.getName() + " :green_circle:", ":green_circle:");
            SlackUtils.sendMessage(slackMessage);
        }
    }

    public String getCaseNumber(Scenario scenario){
        return scenario.getSourceTagNames().toString().replaceAll(REGEX,"");
    }
}
