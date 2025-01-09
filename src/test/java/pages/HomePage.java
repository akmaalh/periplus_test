package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLink(String id) {
        driver.findElement(By.id(id)).click();
    }

    public LoginPage clickFormAuthenticationLink() {
        clickLink("nav-signin-text");
        return new LoginPage(driver);
    }
}
