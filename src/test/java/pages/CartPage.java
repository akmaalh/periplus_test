package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    // locators
    private By quantityField = By.xpath("(//*[@class='row row-cart-product'])[1]//input[@class='input-number text-center']");
    private By plusButton = By.xpath("(//*[@class='row row-cart-product'])[1]//div[@class='button plus']");
    private By productRows = By.xpath("//*[@class='row row-cart-product']");
    private By productRemoveButton = By.xpath("(//*[@class='row row-cart-product'])[1]//*[@class='btn btn-cart-remove']");

    private By cartEmptyMessage = By.xpath("//div[@class='content']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getQuantity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        return driver.findElement(quantityField).getAttribute("value");
    }

    public void clickIncreaseQuantity() {
        driver.findElement(plusButton).click();
    }

    public int getNumberOfProducts() {
        return driver.findElements(productRows).size();
    }

    public void removeProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        if (getNumberOfProducts() > 0) {
            driver.findElement(productRemoveButton).click();
        } else {
            System.out.println("No products to remove");
        }
    }

    public boolean isCartEmpty() {
        return driver.findElement(cartEmptyMessage).getText().contains("Your shopping cart is empty");
    }
}
