package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public  HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[@href='/login?url=%2Fsearch']")
    WebElement loginLink;

    @FindBy(xpath = "//a[@href='/logout?url=%2Fsearch']")
    WebElement logoutLink;


    public boolean isLogoutLinkPresent() {
        try {
            return logoutLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickLoginLink() {
        click(loginLink);
    }


    public void clickLogoutLink() {
        click(logoutLink);
    }
}
