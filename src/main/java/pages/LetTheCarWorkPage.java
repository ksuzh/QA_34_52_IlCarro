package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.Fuel;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);

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

    @FindBy(id="photos")
    WebElement inputImage;

    @FindBy(xpath = "//h2[@class='message']")
    WebElement errorCityPopUp;

    @FindBy(css = ".error")
    List <WebElement> errorNotes;

    @FindBy(xpath = "//div[@class='error']/div")
    WebElement errorNoteYear;

    public void clickBtnSubmitWithJs() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        btnSubmit.click();
    }

    public boolean isBtnSubmitEnabled() {
        return btnSubmit.isEnabled();
    }

    public void typeCarDetailsForm(Car car){
        type(inputLocation, car.getLocation());
        type(inputManufacturer, car.getManufacture());
        type(inputModel, car.getModel());
        type(inputYear, car.getYear());
        chooseFuel(car.getFuel());
        type(inputSeats, String.valueOf(car.getSeats()));
        type(inputCarClass, car.getCarClass());
        type(inputSerialNumber, car.getSerialNumber());
        type(inputPrice, String.valueOf(car.getPrice()));
        type(inputAbout, car.getAbout());
    }

    private void chooseFuel(Fuel fuel){
        inputFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void downloadImage(String fileName){
        inputImage.sendKeys(new File("src/test/resources/"
                + fileName).getAbsolutePath());
    }

    public void clickAllRequiredFields(){
        inputLocation.click();
        inputManufacturer.click();
        inputModel.click();
        inputYear.click();
        inputFuel.click();
        inputSeats.click();
        inputCarClass.click();
        inputSerialNumber.click();
        inputPrice.click();
        inputAbout.click();
    }

    public boolean isCityErrorMessagePopUp() {
        return isTextInElementPresent(errorCityPopUp, "must not be blank");
    }

    public List<String> getErrorNotes() {
        clickAllRequiredFields();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(errorNotes));
        List<String> errorText = new ArrayList<>();
        for (WebElement element : errorNotes)
            errorText.add(element.getText());
        return errorText;
    }

    public void typeYear(String year){
        inputYear.sendKeys(year);
        inputModel.click();
    }

    public boolean isYearErrorMessageDisplayed(String errorMessage) {
        return isTextInElementPresent(errorNoteYear, errorMessage);
    }

}
