package ge.mycredo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private final By authHeaderLocator = By.xpath("//section[@id = 'authloading']//p[contains(@class, 'header')]");
    private final By usernameInputLocator = By.xpath("//input[@id='userName']");
    private final By passwordInputLocator = By.xpath("//input[@id='newPass']");
    private final By loginButtonLocator = By.xpath("//button[@id='submitAuth']");
    private final By errorTextLocator = By.xpath("//p[@id='growlText']");
    private final By errorCloseButtonLocator = By.xpath("//div[contains(@class, 'white') and contains(@class, 'close')]");
    private final By languageSwitcherButtonLocator = By.xpath("//div[@id='languageSwitcherBtn']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getAuthHeader() {
        return findElement(authHeaderLocator);
    }

    public WebElement getUsernameInput() {
        return findElement(usernameInputLocator);
    }

    public WebElement getPasswordInput() {
        return findElement(passwordInputLocator);
    }

    public WebElement getLoginButton() {
        return findElement(loginButtonLocator);
    }

    public WebElement getErrorTextParagraph() {
        return findElement(errorTextLocator);
    }

    public WebElement getErrorCloseButton() {
        return findElement(errorCloseButtonLocator);
    }

    public WebElement getLanguageSwitcherButton() {
        return findElement(languageSwitcherButtonLocator);
    }

}
