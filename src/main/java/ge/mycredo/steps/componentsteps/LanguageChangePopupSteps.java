package ge.mycredo.steps.componentsteps;

import ge.mycredo.pages.components.LanguageChangePopup;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class LanguageChangePopupSteps extends LanguageChangePopup {

    protected SoftAssert softAssert;

    public LanguageChangePopupSteps(WebDriver driver, SoftAssert softAssert, WebDriverWait wait) {
        super(driver, wait);
        this.softAssert = softAssert;
    }

    @Step("Verify language change popup is visible")
    public LanguageChangePopupSteps verifyChangeLanguagePopupHeaderVisibility() {
        wait.until(ExpectedConditions.visibilityOf(getChangeLanguagePopupHeader()));
        return this;
    }

    @Step("Select language: {language}")
    public LanguageChangePopupSteps chooseLanguage(String language) {
        getChangeLanguageButton(language).click();
        return this;
    }
}