package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.enums.HeaderMenu;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    static WebDriver driver;
    public Logger logger = LoggerFactory.getLogger(BasePage.class);

    public void setDriver(WebDriver wd) {
        this.driver = wd;
    }

    @FindBy(xpath = "//div[contains(@class,'error')]")
    List<WebElement> listErrors;

    public <T extends BasePage> T clickHeaderButtons(HeaderMenu item){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(item.getLocator()))).click();
        switch (item){
            case LOGO -> {
                return (T) new HomePage(driver);
            }
            case SEARCH -> {
                return (T) new HomePage(driver);
            }
            case LOGOUT -> {
                return (T) new HomePage(driver);
            }
            case LET_THE_CAR_WORK -> {
                return (T) new LetTheCarWorkPage(driver);
            }
            case TERMS_OF_USE -> {
                return (T) new TermsOfUsePage(driver);
            }
            case SIGN_UP -> {
                return (T) new RegistrationPage(driver);
            }
            case LOGIN -> {
                return (T) new LoginPage(driver);
            }
            case DELETE_ACCOUNT -> {
                return (T) new PopUpPage(driver);
            }
            default -> throw new IllegalArgumentException("Wrong item");
        }
    }

    public boolean isTextInErrorPresent(String text) {
        if (listErrors == null || listErrors.isEmpty()) {
            return false;
        }
        for (WebElement element : listErrors) {
            if (element.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        element.click();
        element.sendKeys(text);
    }

    public void clickWait(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }


    public boolean isElementPresent(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e){
//            e.printStackTrace();
//            System.out.println("created exception");
            logger.error("created exception: ", e);
        }
        return false;
    }

    public void clickBtnWithJs(WebElement btn) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        btn.click();
    }

    public boolean isUrlContainsText(String text){
        try{
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains(text));
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }
}
