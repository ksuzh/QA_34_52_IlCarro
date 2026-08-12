package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    static WebDriver driver;

    public void setDriver(WebDriver wd) {
        this.driver = wd;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        element.click();
        element.sendKeys(text);
    }

//    public void clickYallaButton(WebElement element) {
//        element.click();
//    }

    public boolean isElementPresent(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("created exception");
        }
        return false;
    }
}
