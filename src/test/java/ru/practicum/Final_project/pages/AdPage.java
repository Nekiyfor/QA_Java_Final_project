package ru.practicum.Final_project.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AdPage {
    private final SelenideElement nameOfAdField = $(By.name("name"));
    private final SelenideElement dropCategoryButton = $(By.xpath("//button[contains(@class, 'dropDownMenu_arrowDown')]"));
    private final SelenideElement productDescriptionField = $(By.cssSelector("textarea[placeholder='Описание товара']"));
    private final SelenideElement priceField = $(By.name("price"));
    private final SelenideElement publishButton = $(By.xpath("//button[text()='Опубликовать']"));
    private final SelenideElement saveChangesButton = $(By.xpath("//button[text()='Сохранить изменения']"));
    private final SelenideElement adDeletionButton = $(By.xpath("//button[text() = 'Удалить']"));

    public void creationAd(String nameOfAd, String categoryName, String productDescription, String price) {
        nameOfAdField.shouldBe(visible).setValue(nameOfAd);
        dropCategoryButton.click();
        // из доступных категорий только: Авто, Книги, Садоводство, Хобби, Технологии.
        String category = String.format("//span[text() = '%s']", categoryName);
        $(By.xpath(category)).click();
        productDescriptionField.shouldBe(visible).setValue(productDescription);
        priceField.shouldBe(visible).setValue(price);
    }

    public void clickPublishButton() {
        publishButton.click();
    }

    public void clickSaveChangesButton() {
        saveChangesButton.click();
    }

    public void clickAdDeletionButton() {
        adDeletionButton.click();
    }
}
