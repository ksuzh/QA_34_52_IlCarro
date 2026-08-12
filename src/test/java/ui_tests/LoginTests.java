package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static utils.PositiveUserFactory.positiveUser;

public class LoginTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void  openLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickLoginLink();
        loginPage = new LoginPage(getDriver());

    }

    @Test
	public void  positiveLoginTest () {
        User userLogin = User.builder()
                .email("w1@gmail.com")
                .password("Qwerty!123")
                .build();
        loginPage.typeLoginForm(userLogin);
        loginPage.clickYallaBtn();
        Assert.assertTrue(loginPage.isSuccessDialogueMsg("Logged in success"));
        loginPage.clickCloseDialogueBtn();
    }

    @Test
    public void  negativeLoginTestNonExistingUser () {
        User user = positiveUser();
        loginPage.typeLoginForm(user);
        loginPage.clickYallaBtn();
        Assert.assertTrue(loginPage.isLoginFailedDialogueMsg("Login failed"));
        loginPage.clickCloseDialogueBtn();
    }

    @Test
    public void  negativeLoginTestEmptyFields () {
        Assert.assertFalse(loginPage.isYallaBtnEnabled());
    }

    @AfterMethod
    public void  logout() {
        if(homePage.isLogoutLinkPresent()){
            homePage.clickLogoutLink();
        }
    }

}
