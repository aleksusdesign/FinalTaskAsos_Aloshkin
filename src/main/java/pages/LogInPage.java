package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BootsPage {

    @FindBy(xpath = "//input[@name='Username']")
    private WebElement emailInput;

    @FindBy(xpath = "//span[@id='EmailAddress-error']")
    private WebElement emailError;

    @FindBy(xpath = "//span[@id='Password-error']")
    private WebElement passwordError;

    @FindBy(xpath = "//li[@id='loginErrorMessage']")
    private WebElement logInError;

    @FindBy(xpath = "//input[@name='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='signin']")
    private WebElement signInButton;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailInputVisible(){return emailInput.isDisplayed();}

    public boolean isPasswordInputVisible(){return emailInput.isDisplayed();}

    public boolean isSignInButtonVisible(){return signInButton.isDisplayed();}

    public boolean isEmailErrorVisible(){return emailError.isDisplayed();}

    public boolean isPasswordErrorVisible(){return passwordError.isDisplayed();}

    public boolean isLogInErrorVisible(){return logInError.isDisplayed();}

    public void insertEmailInput(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void insertPasswordInput(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton(){
        signInButton.click();
    }

}
