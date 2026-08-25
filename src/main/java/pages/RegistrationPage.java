package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {

    LoginPage loginPage;
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
        LoginPage loginPage = new LoginPage(driver);
    }

    @FindBy(xpath = "//input[@id='name']")
    WebElement nameField;

    @FindBy(xpath = "//input[@id='lastName']")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='terms-of-use']")
    WebElement checkBoxTermsOfUse;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement yallaBtn;

    @FindBy(xpath = "//h1[text()='Registered']")
    WebElement registeredDialogueMsg;

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxLabel;


    public void typeRegistrationForm(User user) {
        type(nameField, user.getFirstName());
        type(lastNameField, user.getLastName());
        type(emailField, user.getEmail());
        type(passwordField, user.getPassword());
        //clickCheckboxTermsOfUse();
        clickCheckboxWithActions();
    }

    public void clickYallaBtn() {
        click(yallaBtn);
    }

    public void clickCheckboxTermsOfUse() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBoxTermsOfUse);
    }

    public void clickCheckboxWithActions(){
        int x = checkBoxLabel.getSize().getWidth();
        int y = checkBoxLabel.getSize().getHeight();
        System.out.println("x = " + x + " y = " + y);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBoxLabel, - x / 10 * 3, - y / 2).click().perform();
    }

//    public void clickCheckBoxTermsOfUse() {
//        new Actions(driver)
//                .scrollToElement(checkBoxTermsOfUse)
//                .moveToElement(checkBoxTermsOfUse, 5, 0)
//                .click()
//                .perform();
//    }


    public boolean isRegisteredDialogueMsg() {
        return isElementPresent(registeredDialogueMsg);
    }

}
