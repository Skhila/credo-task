package ge.mycredo;

import ge.mycredo.data.CredoDataProvider;
import ge.mycredo.data.Routes;
import ge.mycredo.steps.LoginPageSteps;
import ge.mycredo.steps.componentsteps.LanguageChangePopupSteps;
import ge.mycredo.utils.config.BaseTest;
import ge.mycredo.utils.config.RetryAnalyzer;
import ge.mycredo.utils.config.testlisteners.ScreenshotListener;
import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
@Epic("Authentication")
@Feature("Login Functionality")
public class MyCredoTest extends BaseTest {

    LoginPageSteps loginPageSteps;
    LanguageChangePopupSteps languageChangePopupSteps;

    @BeforeMethod
    public void setup() {
        driver.get(Routes.LOGIN_URL);
        loginPageSteps = new LoginPageSteps(getDriver(), softAssert, wait);
        languageChangePopupSteps = new LanguageChangePopupSteps(getDriver(), softAssert, wait);
    }

    @Test(priority = 1, dataProvider = "negativeLoginDataProvider", dataProviderClass = CredoDataProvider.class,
        retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify system behavior with invalid login credentials across different languages")
    @Story("As a user, I should see appropriate error messages in my selected language when I enter invalid credentials")
    public void loginWithInvalidCredentialsAcrossDifferentLanguages(String language, String headerText, String userName, String password, String errorText) {
        loginPageSteps
                .clickLanguageSwitcherButton();

        languageChangePopupSteps
                .verifyChangeLanguagePopupHeaderVisibility()
                .chooseLanguage(language);

        loginPageSteps
                .verifyHeaderText(headerText)
                .fillUserName(userName)
                .fillPassword(password)
                .clickLoginButton()
                .verifyErrorText(errorText)
                .clickErrorCloseButton();

        // ყოველ ტესტ მეთოდში ცალკე ვიძახებ assertAll()-ს რადგან AfterMethod()-ში გამოძახების შემთხვევაში ტესტ მეთოდის ნაცვლად
        // AfterMethod() ერორდება და კითხვადობას უკარგავს ტესტის შედეგებს
        softAssert.assertAll();
    }

    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify system behavior with empty username")
    @Story("As a user, I should not be able to attempt login with an empty username to maintain security")
    public void loginWithEmptyUserName() {
        loginPageSteps
                .fillUserName("")
                .fillPassword(RandomStringUtils.randomAlphanumeric(10))
                .verifyLoginButtonIsDisabled();

        softAssert.assertAll();
    }

    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify system behavior with empty password")
    @Story("As a user, I should not be able to attempt login with an empty password to maintain security")
    public void loginWithEmptyPassword() {
        loginPageSteps
                .fillUserName(RandomStringUtils.randomAlphanumeric(10))
                .fillPassword("")
                .verifyLoginButtonIsDisabled();

        softAssert.assertAll();
    }
}
