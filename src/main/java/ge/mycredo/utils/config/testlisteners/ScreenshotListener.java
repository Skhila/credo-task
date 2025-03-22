package ge.mycredo.utils.config.testlisteners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
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
        WebDriver driver = getDriverFromTest(testInstance);

        if (driver != null) {
            try {
                String testMethodName = result.getMethod().getMethodName();

                saveScreenshot(driver, testMethodName);

                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(
                        testMethodName + "_failure",
                        "image/png",
                        new ByteArrayInputStream(screenshotBytes),
                        "png"
                );

                System.out.println("Screenshot captured for failed test: " + testMethodName);
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    @Attachment(value = "Screenshot of {testName}", type = "image/png")
    private byte[] saveScreenshot(WebDriver driver, String testName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private WebDriver getDriverFromTest(Object testInstance) {
        try {
            try {
                return (WebDriver) testInstance.getClass().getMethod("getDriver").invoke(testInstance);
            } catch (NoSuchMethodException e) {
                try {
                    return (WebDriver) testInstance.getClass().getField("driver").get(testInstance);
                } catch (NoSuchFieldException f) {
                    java.lang.reflect.Field field = testInstance.getClass().getDeclaredField("driver");
                    field.setAccessible(true);
                    return (WebDriver) field.get(testInstance);
                }
            }
        } catch (Exception e) {
            System.err.println("Could not find WebDriver: " + e.getMessage());
            return null;
        }
    }
}
