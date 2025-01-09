package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AddtoCartTest extends BaseTest {

    @Test
    public void testAddtoCartbyClickProduct() {
        LoginPage loginPage = homePage.clickFormAuthenticationLink();
        loginPage.setEmail("malgaming2106@gmail.com");
        loginPage.setPassword("Peri+test");
        AccountPage accountPage = loginPage.clickLoginButton();

        String bookTitle = accountPage.getBookTitle(1);

        ProductDetailPage productDetailPage = accountPage.clickBookTitle(1);

        productDetailPage.clickAddToCartButton();

        String responseMessage = productDetailPage.getModalMessage();

        productDetailPage.waitProgram();

        productDetailPage.hoverOverCartButton();

        Assert.assertEquals(responseMessage, "Success add to cart", "Incorrect message");
        Assert.assertTrue(productDetailPage.getAddedBookTitles().contains(bookTitle), "Book not added to cart");
    }

    @Test
    public void testAddtoCartbyHover() {
        LoginPage loginPage = homePage.clickFormAuthenticationLink();
        loginPage.setEmail("malgaming2106@gmail.com");
        loginPage.setPassword("Peri+test");
        AccountPage accountPage = loginPage.clickLoginButton();

        String bookTitle = accountPage.getBookTitle(1);

        accountPage.hoverOverBookTitle(1);
        accountPage.clickAddToCartButton(1);

        accountPage.hoverOverCartButton();

        Assert.assertTrue(accountPage.getAddedBookTitles().contains(bookTitle), "Book not added to cart");
    }

    @Test
    public void testLogoutConsistency() {
        // Test data
        String email = "malgaming2106@gmail.com";
        String password = "Peri+test";

        LoginPage loginPage = homePage.clickFormAuthenticationLink();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        AccountPage accountPage = loginPage.clickLoginButton();

        String bookTitle = accountPage.getBookTitle(1);

        accountPage.hoverOverBookTitle(1);
        accountPage.clickAddToCartButton(1);

        accountPage.hoverOverCartButton();

        LogoutPage logoutPage = accountPage.clickLogoutButton();

        loginPage = logoutPage.clickFormAuthenticationLink();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        accountPage = loginPage.clickLoginButton();

        accountPage.hoverOverCartButton();

        Assert.assertTrue(accountPage.getAddedBookTitles().contains(bookTitle), "Book not added to cart");
    }
}
