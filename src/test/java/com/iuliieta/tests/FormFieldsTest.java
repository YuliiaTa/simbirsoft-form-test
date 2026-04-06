package com.iuliieta.tests;

import com.iuliieta.pages.FormFieldsPage;
import com.iuliieta.tests.listeners.AllureListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(AllureListener.class)
public class FormFieldsTest {

    private WebDriver driver;
    private FormFieldsPage formFieldsPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        AllureListener.setDriver(driver);

        formFieldsPage = new FormFieldsPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Позитивный тест: Заполнение всей формы и проверка alert")
    public void testSuccessfulFormSubmission() {
        String alertMessage = formFieldsPage
                .open()
                .enterName("Юля Таратенко")
                .enterPassword("SecurePass123!")
                .selectMilkAndCoffee()
                .selectYellowColor()
                .selectAutomationPreference("Yes")
                .enterEmail("yulia@example.com")
                .fillMessageWithLongestTool()
                .clickSubmit()
                .getAlertAndAccept();
        assertEquals(alertMessage, "Message received!",
                "Alert должен содержать текст 'Message received!'");
    }
    
    @Test
    @Description("Альтернативный Позитивный тест: Заполнение всей формы и проверка alert")
    public void testAlternativeFormSubmission() {
        String alertMessage = formFieldsPage
                .open()
                .enterName("Yulia Taratenko")
                .enterPassword("password123")
                .selectDrinks("Milk", "Coffee")
                .selectColor("Yellow")
                .selectAutomationPreference("No")
                .enterEmail("yulia@example.com")
                .fillMessageWithLongestTool()
                .clickSubmit()
                .getAlertAndAccept();

        assertEquals(alertMessage, "Message received!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}