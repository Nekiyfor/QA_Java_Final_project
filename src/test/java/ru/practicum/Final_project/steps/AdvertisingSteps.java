package ru.practicum.Final_project.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.practicum.Final_project.hooks.BrowserHooks;
import ru.practicum.Final_project.pages.AdPage;
import ru.practicum.Final_project.pages.HomePage;
import ru.practicum.Final_project.pages.ProfilePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class AdvertisingSteps {

    private final HomePage homePage;
    private final AdPage adPage;
    private final ProfilePage profilePage;

    public AdvertisingSteps(HomePage homePage, AdPage adPage, ProfilePage profilePage) {
        this.homePage = homePage;
        this.adPage = adPage;
        this.profilePage = profilePage;
    }

    @When("Нажимаем кнопку \"Разместить объявление\"")
    public void clickPlaceAnAdButton() {
        homePage.clickPlaceAnAdButton();
    }

    @When("Заполняем форму объявления данными:")
    public void fillAdForm(io.cucumber.datatable.DataTable dataTable) {
        List<String> row = dataTable.row(0);
        String nameOfAd = row.get(0);
        String categoryName = row.get(1);
        String productDescription = row.get(2);
        String price = row.get(3);

        adPage.creationAd(nameOfAd, categoryName, productDescription, price);
    }

    @When("Нажимаем кнопку \"Опубликовать\"")
    public void clickPublishButton() {
        adPage.clickPublishButton();
    }

    @When("Открываем редактор объявления")
    public void openEditAd() {
        open(BrowserHooks.BASE_URL + "/profile");
        profilePage.clickAdEditButton();
    }

    @When("Открываем карточку объявления")
    public void openAdCard() {
        open(BrowserHooks.BASE_URL + "/profile");
        profilePage.clickAdCard();
    }

    @When("Нажимаем кнопку удаления объявления")
    public void clickDeleteAdButton() {
        adPage.clickAdDeletionButton();
    }

    @Then("Отображается \"Главная страница\"")
    public void verifyHomePageIsDisplayed() {
        homePage.homePageIsDisplayed();
    }
}
