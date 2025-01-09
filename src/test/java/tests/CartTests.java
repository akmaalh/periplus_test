package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.CartPage;
import pages.LoginPage;

public class CartTests extends BaseTest {
    @Test
    public void testUpdateCartQuantity() {
        LoginPage loginPage = homePage.clickFormAuthenticationLink();
        loginPage.setEmail("malgaming2106@gmail.com");
        loginPage.setPassword("Peri+test");
        AccountPage accountPage = loginPage.clickLoginButton();

        CartPage cartPage = accountPage.clickCartButton();

        String bookQuantity = cartPage.getQuantity();

        int quantity = Integer.parseInt(bookQuantity);

        cartPage.clickIncreaseQuantity();

        String newBookQuantity = cartPage.getQuantity();

        int newQuantity = Integer.parseInt(newBookQuantity);

        Assert.assertEquals(newQuantity, quantity + 1, "Quantity not updated");
    }

    @Test
    public void testRemoveBookFromCart() {
        LoginPage loginPage = homePage.clickFormAuthenticationLink();
        loginPage.setEmail("malgaming2106@gmail.com");
        loginPage.setPassword("Peri+test");
        AccountPage accountPage = loginPage.clickLoginButton();

        accountPage.hoverOverBookTitle(1);
        accountPage.clickAddToCartButton(1);

        accountPage.hoverOverBookTitle(2);
        accountPage.clickAddToCartButton(2);

        CartPage cartPage = accountPage.clickCartButton();

        int bookCount = cartPage.getNumberOfProducts();

        cartPage.removeProduct();

        if (bookCount > 1) {
            Assert.assertEquals(cartPage.getNumberOfProducts(), bookCount - 1, "Book not removed from cart");
        } else {
            Assert.assertTrue(cartPage.isCartEmpty(), "Cart not empty");
        }
    }
}
