package ui_tests;

import dto.Car;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.enums.HeaderMenu;

import static utils.PropertiesReader.getProperty;

public class LetCarWorkTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void openLetCarWorkPageSignedIn() {
        homePage = new HomePage(getDriver());
        homePage.clickLoginLink();
        loginPage = new LoginPage(getDriver());
 //       loginPage = homePage.clickHeaderButtons(HeaderMenu.LOGIN);
        User userLogin = User.builder()
                .email(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(userLogin);
        loginPage.clickYallaBtn();
        loginPage.clickCloseDialogueBtn();
        homePage.clickLetCarWorkLink();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
 //       letTheCarWorkPage = homePage.clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
    }

//    @Test
//    public void addCarPositiveTest() {
//        Car car = Car.builder()
//                .location(getProperty("car.properties", "location"))
//                .manufacture(getProperty("car.properties", "manufacture"))
//                .model(getProperty("car.properties", "model"))
//                .year(getProperty("car.properties", "year"))
//                .chooseFuel
//                .seats(Integer.parseInt(getProperty("car.properties", "seats")))
//                .carClass(getProperty("car.properties", "carClass"))
//                .serialNumber(getProperty("car.properties", "serialNumber"))
//                .price(Double.parseDouble(getProperty("car.properties", "price")))
//                .about(getProperty("car.properties", "about"))
//                .build();
//        letTheCarWorkPage.typeCarDetailsForm(car);
//        letTheCarWorkPage.clickBtnSubmitWithJs();
//        Assert.assertTrue(letTheCarWorkPage.isCityErrorMessagePopUp());
//    }

//    @Test
//    public void addCarLessonPositiveTest() {
//        Car car = Car.builder()
//                .location(getProperty("car.properties", "location"))
//                .manufacture(getProperty("car.properties", "manufacture"))
//                .model(getProperty("car.properties", "model"))
//                .year(Integer.parseInt(getProperty("car.properties", "year")))
//                .fuel(getProperty("car.properties", "fuel"))
//                .seats(Integer.parseInt(getProperty("car.properties", "seats")))
//                .carClass(getProperty("car.properties", "carClass"))
//                .serialNumber(getProperty("car.properties", "serialNumber"))
//                .price(Double.parseDouble(getProperty("car.properties", "price")))
//                .about(getProperty("car.properties", "about"))
//                .build();
//        letTheCarWorkPage.typeCarDetailsForm(car);
//        letTheCarWorkPage.clickBtnSubmitWithJs();
//        Assert.assertTrue(letTheCarWorkPage.isCityErrorMessagePopUp());
//    }
}
