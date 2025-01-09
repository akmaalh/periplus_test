package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailPage {
    private WebDriver driver;

    // locators
    private By addToCartButton = By.className("btn-add-to-cart");
    private By addedBookTitles = By.xpath("//div[@class='shopping-item']//li//div[2]/a");
    private By cartButton = By.id("show-your-cart");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        driver.findElement(addToCartButton).click();
    }

    public String getModalMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".modal-text"))).getText();
    }

    public List<String> getAddedBookTitles() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-text")));
        List<WebElement> addedBooks = driver.findElements(addedBookTitles);
        return addedBooks.stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public void hoverOverCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        WebElement cart = driver.findElement(cartButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(cart).perform();
    }

    public void waitProgram() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
