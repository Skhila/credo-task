package ge.mycredo.utils.config.testlisteners;

import ge.mycredo.utils.config.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();

        try {
            WebDriver driver = null;

            try {
                driver = (WebDriver) testInstance.getClass().getMethod("getDriver").invoke(testInstance);
            } catch (Exception e) {
                System.err.println("Could not get WebDriver: " + e.getMessage());
                return;
            }

            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment(
                        "Screenshot",
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        "png"
                );

                Allure.addAttachment(
                        "Test method",
                        "text/plain",
                        new ByteArrayInputStream(result.getName().getBytes()),
                        "txt"
                );

                System.out.println("Screenshot captured for: " + result.getName());
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
