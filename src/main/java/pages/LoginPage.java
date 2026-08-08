package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {  
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement yallaBtn;

    @FindBy(xpath = "//h2[text()='Logged in success']")
    WebElement successDialogueMsg;

    @FindBy(xpath = "//button[text()='Ok']")
    WebElement closeSuccessDialogueBtn;


    public void typeLoginForm(User userLogin) {
        type(emailField, userLogin.getEmail());
        type(passwordField, userLogin.getPassword());
    }

    public void clickYallaBtn() {
        click(yallaBtn);
    }

    public boolean isSuccessDialogueMsg() {
        return isElementPresent(successDialogueMsg);
    }

    public void clickCloseSuccessDialogueBtn() {
        click(closeSuccessDialogueBtn);
    }


}
