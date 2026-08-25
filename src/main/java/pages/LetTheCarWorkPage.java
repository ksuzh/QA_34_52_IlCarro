package pages;

import dto.Car;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
        LoginPage loginPage = new LoginPage(driver);
    }
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;

    @FindBy(id = "pickUpPlace")
    WebElement inputLocation;

    @FindBy(id = "make")
    WebElement inputManufacturer;

    @FindBy(id = "model")
    WebElement inputModel;

    @FindBy(id = "year")
    WebElement inputYear;

    @FindBy(id = "fuel")
    WebElement inputFuel;

    @FindBy(id = "seats")
    WebElement inputSeats;

    @FindBy(id = "class")
    WebElement inputCarClass;

    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;

    @FindBy(id = "price")
    WebElement inputPrice;

    @FindBy(id = "about")
    WebElement inputAbout;

    @FindBy(xpath = "//h2[@class='message']")
    WebElement errorCityPopUp;

    public void clickBtnSubmitWithJs() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        btnSubmit.click();
    }

    public void typeCarDetailsForm(Car car){
        type(inputLocation, car.getLocation());
        type(inputManufacturer, car.getManufacture());
        type(inputModel, car.getModel());
        type(inputYear, String.valueOf(car.getYear()));
        type(inputFuel, car.getFuel());
        type(inputSeats, String.valueOf(car.getSeats()));
        type(inputCarClass, car.getCarClass());
        type(inputSerialNumber, car.getSerialNumber());
        type(inputPrice, String.valueOf(car.getPrice()));
        type(inputAbout, car.getAbout());
    }

    public boolean isCityErrorMessagePopUp() {
        return isTextInElementPresent(errorCityPopUp, "must not be blank");
    }


}
