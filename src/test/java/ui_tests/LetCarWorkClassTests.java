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
import pages.PopUpPage;

import utils.enums.HeaderMenu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static utils.CarFactory.*;

import static utils.PropertiesReader.getProperty;

public class LetCarWorkClassTests extends AppManager {
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void goToLetTheCarWorkPage() {
        //new HomePage(getDriver()).clickBtnLogin();
        //loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LOGIN);
        User user = User.builder()
                .email(getProperty("base.properties",
                        "email"))
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickYallaBtn();
        new PopUpPage(getDriver()).clickBtnOk();
        letTheCarWorkPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest(){
        Car car = positiveCar();
        System.out.println(car);
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.downloadImage("Image20260830184225.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJs();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));
    }

    // Homework Negative Tests
    //1. only click btn submit
    //2. click all fields and btn submit
    //3. leave one field blank
    //4. wrong year

    @Test
    public void addNewCarFieldsEmptyNoClickNegativeTest(){
        letTheCarWorkPage.clickBtnSubmitWithJs();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("must not be blank"));
    }

    @Test
    public void allFieldsEmptyNoClickNoJsNegativeTest() {
        Assert.assertFalse(letTheCarWorkPage.isBtnSubmitEnabled());
    }

    @Test
    public void allFieldsEmptyWithClickNegativeTest() {
//        letTheCarWorkPage.clickAllRequiredFields();
        List<String> actualErrors = letTheCarWorkPage.getErrorNotes();
        List<String> expectedErrors = List.of(
                "Wrong address",
                "Make is required",
                "Model is required",
                "Year required",
                "Fuel is required",
                "Number of seats is required",
                "Car class is required",
                "Car registration number is required",
                "Price is required");
        System.out.println(actualErrors.size());
        System.out.println(actualErrors);
//        Assert.assertEquals(actualErrors.size(), expectedErrors.size());
        Assert.assertTrue(actualErrors.containsAll(expectedErrors));
    }

    @Test
    public void oneFieldEmptyNegativeTest(){
        Car car = positiveCar();
        car.setPrice(null);
        letTheCarWorkPage.typeCarDetailsForm(car);
        letTheCarWorkPage.downloadImage("Image20260830184225.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJs();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("pricePerDay\":\"must not be null"));
    }

    @Test
    public void nextYearNegativeTest(){
        letTheCarWorkPage.typeYear(String.valueOf(LocalDate.now().getYear() + 1));
        Assert.assertTrue(letTheCarWorkPage.isYearErrorMessageDisplayed("Wrong year"));

    }

    @Test
    public void threeDigitsYearNegativeTest(){
        letTheCarWorkPage.typeYear("202");
        Assert.assertTrue(letTheCarWorkPage.isYearErrorMessageDisplayed("Wrong year"));
    }

    @Test
    public void letteringYearNegativeTest(){
        letTheCarWorkPage.typeYear("202e");
        Assert.assertTrue(letTheCarWorkPage.isYearErrorMessageDisplayed("Year required"));
    }

}
