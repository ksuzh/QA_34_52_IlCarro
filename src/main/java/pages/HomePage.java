package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

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

    @FindBy(xpath = "//a[@href='/registration?url=%2Fsearch']")
    WebElement signUpLink;

    @FindBy(xpath = "//a[@href='/let-car-work']")
    WebElement letCarWorkLink;

    @FindBy(id="city")
    WebElement inputCity;

    @FindBy(id="dates")
    WebElement inputDates;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//div[@class = 'error']")
    WebElement errorMessage;

    @FindBy(xpath = "//span[@class= 'description']")
    WebElement backArea;

    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearOnCalendar;

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

    public void clickSignUpLink() {
        click(signUpLink);
    }


    public void clickLogoutLink() {
        click(logoutLink);
    }

    public void clickLetCarWorkLink() {
        clickWait(letCarWorkLink);
    }

    public void clickBtnYalla() {
        clickBtnWithJs(btnYalla);
    }

    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        System.out.println(startDate.getMonthValue());
//        System.out.println(startDate.getDayOfMonth());
        String dates =
                startDate.getMonthValue() + "/"
                        + startDate.getDayOfMonth() + "/"
                        + startDate.getYear() + " - "
                        + endDate.getMonthValue() + "/"
                        + endDate.getDayOfMonth() + "/"
                        + endDate.getYear();
        System.out.println(dates);
        inputDates.sendKeys(dates);
    }

    public void typeSearchFormWithCalendar(String city,
                                           LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
    }

    private void typeCalendar(LocalDate date) {
        btnYearOnCalendar.click();
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement
                (By.xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();
        String month = createMonth(date.getMonth().toString());
        System.out.println(month);
        WebElement btnMonth = driver.findElement(By
                .xpath("//td[@aria-label='" + month + " " + year + "']"));
        btnMonth.click();
        System.out.println(date.getDayOfMonth());
        String day = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement(By
                .xpath("//td[@aria-label='" + month + " " + day + ", " + year + "']"));
        btnDay.click();

    }

    private String createMonth(String month) {
        return new StringBuilder().append(month
                .substring(0,1).toUpperCase()).append(month.substring(1).toLowerCase()).toString();
    }

    public void clickEmptyFieldsSearchForm() {
        click(inputDates);
 //       click(inputDates);
//        click(backArea);
//        inputDates.sendKeys(Keys.TAB);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].blur();", inputDates);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(errorMessage));
        click(btnYalla);
    }
     public boolean isbtnYallaDisabled() {
        return  btnYalla.isEnabled();
     }

}
