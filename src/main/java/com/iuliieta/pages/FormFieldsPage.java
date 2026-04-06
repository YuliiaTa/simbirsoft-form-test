package com.iuliieta.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FormFieldsPage extends BasePage {

    private static final String URL = "https://practice-automation.com/form-fields/";

    public FormFieldsPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @FindBy(id = "name-input")
    private WebElement nameInput;

    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;

    @FindBy(id = "drink2")
    private WebElement milkCheckbox;

    @FindBy(id = "drink3")
    private WebElement coffeeCheckbox;

    @FindBy(css = "input[type='checkbox'][name='fav_drink']")
    private List<WebElement> allDrinkCheckboxes;

    @FindBy(xpath = "//input[@id='color3']")
    private WebElement yellowRadio;

    @FindBy(css = "input[type='radio'][name='fav_color']")
    private List<WebElement> allColorRadios;

    @FindBy(id = "automation")
    private WebElement automationDropdown;

    @FindBy(css = "input[id='email']")
    private WebElement emailInput;

    @FindBy(id = "message")
    private WebElement messageInput;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    @FindBy(css = "ul li")
    private List<WebElement> automationToolsList;


    private void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void safeClick(WebElement element) {
        scrollToElement(element);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    public FormFieldsPage open() {
        System.out.println("Открытие страницы");
        driver.get(URL);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scrollToTop();
        return this;
    }
    public FormFieldsPage enterName(String name) {
        System.out.println("Имя: " + name);
        safeClick(nameInput);
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }
    public FormFieldsPage enterPassword(String password) {
        System.out.println("Ввод пароля");
        safeClick(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public FormFieldsPage selectMilkAndCoffee() {
        System.out.println("Выбор Milk и Coffee");
        safeClick(milkCheckbox);
        safeClick(coffeeCheckbox);
        return this;
    }

    public FormFieldsPage selectDrinks(String... drinks) {
        System.out.println("Выбор напитков");
        for (WebElement drink : allDrinkCheckboxes) {
            String drinkId = drink.getAttribute("id");
            WebElement label = driver.findElement(By.cssSelector("label[for='" + drinkId + "']"));
            String drinkText = label.getText();

            for (String selectedDrink : drinks) {
                if (drinkText.equals(selectedDrink) && !drink.isSelected()) {
                    safeClick(drink);
                }
            }
        }
        return this;
    }

    public FormFieldsPage selectYellowColor() {
        System.out.println("Выбор Yellow цвета");
        safeClick(yellowRadio);
        return this;
    }

    public FormFieldsPage selectColor(String colorName) {
        System.out.println("Выбор цвета: " + colorName);
        for (WebElement radio : allColorRadios) {
            String radioId = radio.getAttribute("id");
            WebElement label = driver.findElement(By.cssSelector("label[for='" + radioId + "']"));
            String labelText = label.getText();

            if (labelText.equals(colorName) && !radio.isSelected()) {
                safeClick(radio);
                break;
            }
        }
        return this;
    }

    public FormFieldsPage selectAutomationPreference(String choice) {
        System.out.println("Выбор варианта: " + choice);
        safeClick(automationDropdown);
        Select dropdown = new Select(automationDropdown);
        dropdown.selectByVisibleText(choice);
        return this;
    }

    public FormFieldsPage enterEmail(String email) {
        System.out.println("Ввод email: " + email);
        safeClick(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public FormFieldsPage fillMessageWithLongestTool() {
        System.out.println("Заполнение Message");
        List<String> toolNames = automationToolsList.stream()
                .map(WebElement::getText)
                .filter(text -> text != null && !text.isEmpty())
                .collect(Collectors.toList());
        System.out.println("Найдено инструментов: " + toolNames.size());
        System.out.println("Список: " + toolNames);
        int toolsCount = toolNames.size();
        String longestTool = toolNames.stream()
                .max((tool1, tool2) -> Integer.compare(tool1.length(), tool2.length()))
                .orElse("Не найдено");
        System.out.println("Самый длинный инструмент: " + longestTool);
        String message = String.format("%d %s", toolsCount, longestTool);
        System.out.println("Сообщение: " + message);

        safeClick(messageInput);
        messageInput.clear();
        messageInput.sendKeys(message);

        return this;
    }

    public FormFieldsPage clickSubmit() {
        safeClick(submitButton);
        return this;
    }

    public String getAlertAndAccept() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert текст: " + alertText);
        alert.accept();
        return alertText;
    }
}