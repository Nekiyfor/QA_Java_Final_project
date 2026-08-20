package ru.practicum.Final_project.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

    private final SelenideElement homePage = $(By.xpath("//div[contains(@class, 'homePage')]"));
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Вход и регистрация']"));
    private final SelenideElement logoutButton = $(By.xpath("//button[text()='Выйти']"));
    private final SelenideElement placeAnAdButton = $(By.xpath("//button[text()='Разместить объявление']"));
    private final ElementsCollection allTitles = $$("h2");

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

    public void verifyAdIsVisible(String expectedTitle) {
        allTitles
                .filterBy(Condition.textCaseSensitive(expectedTitle.trim()))
                .shouldHave(size(1));
    }

    public void verifyAdIsNotVisible(String expectedTitle) {
        homePage.shouldBe(visible);
        allTitles
                .filterBy(Condition.textCaseSensitive(expectedTitle.trim()))
                .shouldBe(CollectionCondition.empty);
    }

}