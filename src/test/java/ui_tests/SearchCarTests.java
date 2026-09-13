package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.TestNGListener;
import utils.WDListener;

import java.time.LocalDate;

@Listeners(TestNGListener.class)
public class SearchCarTests extends AppManager {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isUrlContainsText("search/results"));
    }

    @Test
    public void searchCarWithCalendarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now()
                .plusDays(2);
        LocalDate endDate = LocalDate.now()
                .plusDays(8);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
    }

    @Test
    public void searchCarOneDayNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("can't book car for less than a day"));
    }

    @Test
    public void searchCarNegativeMoreOneYearTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now()
                .plusYears(1).plusDays(1);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent
                ("You can't pick date after one year"));
    }

    @Test
    public void searchCarNegativeStartDateAfterEndDateTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now()
                .plusDays(7);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isTextInErrorPresent
                ("Second date must be after first date"));
        softAssert.assertTrue(homePage.isTextInErrorPresent
                ("You can't book car for less than a day"));
        softAssert.assertAll();
    }

    @Test
    public void searchCarStartDateAfterEndDateCalendarNegativeTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now()
                .plusDays(7);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent
                ("Second date must be after first date"));

    }

    @Test
    public void searchCarWithCalendarPastYearNegativeTest() {
        LocalDate startDate = LocalDate.now().minusYears(2);
        Assert.assertTrue(homePage.isYearDisabled(startDate));
    }

    @Test
    public void searchCarEmptyDatesNegativeTest() {
        homePage.clickEmptyFieldsSearchForm();
        Assert.assertTrue(homePage.isTextInErrorPresent("Dates are required"));
    }

    @Test
    public void searchCarNegativeMoreOneYearWithCalendarTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now()
                .plusYears(1).plusDays(1);
        Assert.assertTrue(homePage.isDateDisabled(city, startDate, endDate));

    }
}
