package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // locators
    private By emailField = By.cssSelector("input[name='email']");
    private By passwordField = By.cssSelector("input[name='password']");
    private By loginButton = By.id("button-login");

    private By warningMessage = By.xpath("//div[@class='warning']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // methods
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public AccountPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new AccountPage(driver);
    }

    public String getWarningMessage() {
        return driver.findElement(warningMessage).getText();
    }
}
