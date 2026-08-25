package ui_tests;

import dto.User;
import manager.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static utils.PositiveUserFactory.*;

public class RegistrationTests extends AppManager {
    HomePage homePage;
    RegistrationPage registrationPage;

    @BeforeMethod
    public void  openRegistrationPage() {
        logger.info("Start registration test");
        homePage = new HomePage(getDriver());
        homePage.clickSignUpLink();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void positiveRegistrationTest () {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickYallaBtn();
        Assert.assertTrue(registrationPage.isRegisteredDialogueMsg());
    }

    @Test
    public void positiveRegistrationWithActionTest () {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickYallaBtn();
        Assert.assertTrue(registrationPage.isRegisteredDialogueMsg());
    }
}
