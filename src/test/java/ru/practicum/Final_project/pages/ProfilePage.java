package ru.practicum.Final_project.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement adCard = $(By.xpath("//div[@class = 'card']"));
    private final SelenideElement adEditingButton = $(By.xpath("//button[@class = 'editButton']"));

    public void clickAdCard() {
        adCard.click();
    }

    public void clickAdEditButton() {
        adEditingButton.click();
    }
}
