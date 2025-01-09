package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class AccountPage {
    private WebDriver driver;

    private By addtoCartButton(int index) {
        return By.xpath("(//*[@class='owl-item active'])[" + index + "]//*[@class='ti-shopping-cart']");
    }

    private By addedBookTitles = By.xpath("//div[@class='shopping-item']//li//div[2]/a");

    private By cartButton = By.id("show-your-cart");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAccountPage() {
        return driver.getTitle().contains("My Account");
    }


    public ProductDetailPage clickBookTitle(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        // Wait for active carousel items
        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//*[@class='owl-item active'])[" + index + "]//*[@class='product-content product-contents']/h3")));

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        book.click();

        return productDetailPage;
    }

    public void hoverOverBookTitle(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//*[@class='owl-item active'])[" + index + "]//*[@class='product-content product-contents']/h3")));
        Actions actions = new Actions(driver);
        actions.moveToElement(book).perform();
    }

    public void clickAddToCartButton(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        driver.findElement(addtoCartButton(index)).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CartPage clickCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }

    public void hoverOverCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        WebElement cart = driver.findElement(cartButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(cart).perform();
    }

    public String getBookTitle(int index) {
        return driver.findElement(By.xpath("(//*[@class='owl-item active'])[" + index + "]//*[@class='product-content product-contents']/h3")).getText();
    }

    public List<String> getAddedBookTitles() {
        List<WebElement> addedBooks = driver.findElements(addedBookTitles);
        return addedBooks.stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public LogoutPage clickLogoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("preloader")));
        driver.findElement(By.xpath("//*[@class='box-content']//a[contains(text(),'Logout')]")).click();
        return new LogoutPage(driver);
    }
}
