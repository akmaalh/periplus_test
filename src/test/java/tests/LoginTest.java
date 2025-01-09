package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = homePage.clickFormAuthenticationLink();
        loginPage.setEmail("malgaming2106@gmail.com");
        loginPage.setPassword("Peri+test");
        AccountPage accountPage = loginPage.clickLoginButton();

        Assert.assertTrue(accountPage.isAccountPage(),
                "Alert text is incorrect");
    }

    @Test
    public void testUnsuccessfulLogin() {
        LoginPage loginPage = homePage.clickFormAuthenticationLink();
        loginPage.setEmail("malgaming216@gmail.com");
        loginPage.setPassword("Peri+test");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.getWarningMessage().contains("Warning: No match for Email Address and/or Password."),
                "Alert text is incorrect");
    }
}
