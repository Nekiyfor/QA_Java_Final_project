package ru.practicum.Final_project.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class PopupPage {

    private final SelenideElement emailField = $(By.name("email"));
    private final SelenideElement passwordField = $(By.name("password"));
    private final SelenideElement submitPasswordField = $(By.name("submitPassword"));
    private final SelenideElement registrationButton = $(By.xpath("//button[text()='Создать аккаунт']"));
    private final SelenideElement registrationError = $(By.xpath("//span[text()='Ошибка']"));
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти']"));
    private final SelenideElement noAccountButton = $(By.xpath("//button[text()='Нет аккаунта']"));

    public void fillRegForm(String email, String password) {
        emailField.shouldBe(visible).setValue(email);
        passwordField.shouldBe(visible).setValue(password);
        submitPasswordField.shouldBe(visible).setValue(password);
    }

    public void clickRegButton() {
        registrationButton.click();
    }

    public void regErrorDisplayed() {
        registrationError.shouldBe(Condition.visible);
    }

    public void fillLoginForm(String email, String password) {
        emailField.shouldBe(visible).setValue(email);
        passwordField.shouldBe(visible).setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickNoAccountButton() {
        noAccountButton.click();
    }
}