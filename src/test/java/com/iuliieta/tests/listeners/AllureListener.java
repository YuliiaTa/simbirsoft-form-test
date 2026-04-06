package com.iuliieta.tests.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

public class AllureListener implements ITestListener {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            byte[] screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on failure", "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            byte[] screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on success", "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        }
    }
}