package ru.practicum.Final_project.pages;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private final SelenideElement homePage = $(By.xpath("//div[contains(@class, 'homePage')]"));
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Вход и регистрация']"));
    private final SelenideElement logoutButton = $(By.xpath("//button[text()='Выйти']"));
    private final SelenideElement placeAnAdButton = $(By.xpath("//button[text()='Разместить объявление']"));

    public void homePageIsDisplayed() {
        homePage.shouldBe(visible);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void logoutButtonDisplayed() {
        logoutButton.shouldBe(visible);
    }

    public void clickPlaceAnAdButton() {
        placeAnAdButton.click();
    }
}