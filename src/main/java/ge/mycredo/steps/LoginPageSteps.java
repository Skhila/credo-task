package ge.mycredo.steps;

import ge.mycredo.pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class LoginPageSteps extends LoginPage {

    public SoftAssert softAssert;

    public LoginPageSteps(WebDriver driver, SoftAssert softAssert, WebDriverWait wait) {
        super(driver, wait);
        this.softAssert = softAssert;
    }

    @Step("Fill username field with: {userName}")
    public LoginPageSteps fillUserName(String userName) {
        getUsernameInput().sendKeys(userName);
        return this;
    }

    @Step("Fill password field")
    public LoginPageSteps fillPassword(String password) {
        getPasswordInput().sendKeys(password);
        return this;
    }

    @Step("Click on the login button")
    public LoginPageSteps clickLoginButton() {
        getLoginButton().click();
        return this;
    }

    @Step("Verify error message is visible")
    public LoginPageSteps verifyErrorIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(getErrorTextParagraph()));
        return this;
    }

    @Step("Verify error text: {expectedErrorText}")
    public LoginPageSteps verifyErrorText(String expectedErrorText) {
        softAssert.assertEquals(getErrorTextParagraph().getText(), expectedErrorText, "Incorrect error message!");
        return this;
    }

    @Step("Close the error message")
    public LoginPageSteps clickErrorCloseButton() {
        getErrorCloseButton().click();
        return this;
    }

    @Step("Verify login button is enabled")
    public LoginPageSteps verifyLoginButtonIsEnabled() {
        softAssert.assertTrue(getLoginButton().isEnabled(), "Login button should be enabled!");
        return this;
    }

    @Step("Verify login button is disabled")
    public LoginPageSteps verifyLoginButtonIsDisabled() {
        softAssert.assertTrue(!getLoginButton().isEnabled(), "Login button should be disabled!");
        return this;
    }

    @Step("Verify header text: {expectedHeaderText}")
    public LoginPageSteps verifyHeaderText(String expectedHeaderText) {
        wait.until(ExpectedConditions.textToBePresentInElement(getAuthHeader(), expectedHeaderText));
        return this;
    }

    @Step("Click language switcher button")
    public LoginPageSteps clickLanguageSwitcherButton() {
        getLanguageSwitcherButton().click();
        return this;
    }
}