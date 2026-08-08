package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {


    @BeforeMethod
    public void  openLoginPage() {
        new HomePage(getDriver()).clickLoginLink();

    }

    @Test
	public void  positiveLoginTest () {
        User userLogin = User.builder()
                .email("w1@gmail.com")
                .password("Qwerty!123")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(userLogin);
        loginPage.clickYallaBtn();
        loginPage.isSuccessDialogueMsg();
        loginPage.clickCloseSuccessDialogueBtn();
    }

    @AfterMethod
    public void  logout() {
        new HomePage( getDriver() ).clickLogoutLink();
    }
}
