package ge.mycredo.pages.components;

import ge.mycredo.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanguageChangePopup extends BasePage {
    private final By changeLanguagePopupHeaderLocator = By.xpath("//div[contains(@class, 'popup')]//div[contains(@class, 'txt')]/p[contains(@class, 'header')]");

    public LanguageChangePopup(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getChangeLanguagePopupHeader() {
        return findElement(changeLanguagePopupHeaderLocator);
    }

    public WebElement getChangeLanguageButton(String language) {
        By languageButtonLocator = By.xpath("//ul[contains(@class, 'language-list')]//p[text() = '" + language + "']");
        return findElement(languageButtonLocator);
    }
}
