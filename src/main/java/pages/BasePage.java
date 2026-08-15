package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    static WebDriver driver;

    public void setDriver(WebDriver wd) {
        this.driver = wd;
    }

    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> listErrors;

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
