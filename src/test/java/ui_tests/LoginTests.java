package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import static utils.PositiveUserFactory.positiveUser;
import static utils.PropertiesReader.*;
@Listeners(TestNGListener.class)

public class LoginTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void  openLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickLoginLink();
        loginPage = new LoginPage(getDriver());

    }

    @Test(groups = {"smoke", "regress", "user", "positive"})
	public void  positiveLoginTest () {
        User userLogin = User.builder()
                .email(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(userLogin);
        loginPage.clickYallaBtn();
        Assert.assertTrue(loginPage.isSuccessDialogueMsg("Logged in success"));
        loginPage.clickCloseDialogueBtn();
    }

    @Test
    public void  negativeLoginTestWrongEmail () {
        User user = User.builder()
                .email(getProperty("base.properties", "wrongEmail"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickYallaBtn();
        Assert.assertTrue(loginPage.isLoginFailedDialogueMsg("Login failed"));
        loginPage.clickCloseDialogueBtn();
    }

    @Test
    public void  negativeLoginTestWrongPassword () {
        User user = User.builder()
                .email(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "wrongPassword"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickYallaBtn();
        Assert.assertTrue(loginPage.isLoginFailedDialogueMsg("Login failed"));
        loginPage.clickCloseDialogueBtn();
    }

    @Test
    public void  negativeLoginTestEmptyFields () {
        Assert.assertFalse(loginPage.isYallaBtnEnabled());
    }

    @Test
    public void  negativeLoginTestEmptyFieldsWithClick () {
        User user = User.builder()
                .email("")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickYallaBtn();
        softAssert.assertFalse(loginPage.isYallaBtnEnabled(), "validate isYallaBtnEnabled()");
        System.out.println("test working");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate message: Password is required");
        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void  negativeLoginTestEmptyEmail () {
        User user = User.builder()
                .email("")
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
    //    loginPage.clickYallaBtn();
        Assert.assertTrue(loginPage.isTextInErrorPresent("Email is required"));
    }

    @Test
    public void  negativeLoginTestEmptyPassword () {
        User user = User.builder()
                .email(getProperty("base.properties", "email"))
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickYallaBtn();
        softAssert.assertFalse(loginPage.isYallaBtnEnabled(),
                "validate isYallaBtnEnabled()");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate message: Password is required");
        softAssert.assertAll();
    }

    @AfterMethod
    public void  logout() {
        if(homePage.isLogoutLinkPresent()){
            homePage.clickLogoutLink();
        }
    }

}
