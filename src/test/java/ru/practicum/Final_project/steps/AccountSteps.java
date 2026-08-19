package ru.practicum.Final_project.steps;

import api.LoginResult;
import api.UserProfile;
import api.UserRegData;
import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import ru.practicum.Final_project.hooks.BrowserHooks;
import ru.practicum.Final_project.pages.HomePage;
import ru.practicum.Final_project.pages.PopupPage;
import ru.practicum.Final_project.utils.UserDataUtil;

import static io.restassured.RestAssured.given;

public class AccountSteps {

    private final HomePage homePage;
    private final PopupPage popupPage;
    private String email;
    private String password;

    public AccountSteps(
            HomePage homePage,
            PopupPage popupPage
    ) {
        this.homePage = homePage;
        this.popupPage = popupPage;
    }

    @Given("Генерируем уникальный email")
    public void generateUniqueEmail() {
        email = UserDataUtil.uniqueEmail();
        password = UserDataUtil.getPassword();
    }

    @When("Регистрируем нового пользователя через api")
    public void registerUser() {
        UserRegData NewUser = new UserRegData(email, password);
        given()
                .baseUri(BrowserHooks.BASE_URL)
                .contentType("application/json")
                .body(NewUser)
                .post("/api/signup")
                .then()
                .statusCode(201);
    }

    @When("Авторизуем пользователя")
    public void loginViaApiAndSetLocalStorage() {
        UserRegData userData = new UserRegData(email, password);

        Response response = given()
                .baseUri(BrowserHooks.BASE_URL)
                .header("Content-type", "application/json")
                .body(userData)
                .post("/api/signin")
                .then()
                .statusCode(201)
                .extract()
                .response();

        String accessToken = response.jsonPath().getString("token.access_token");

        UserProfile user = new UserProfile();
        user.id = response.jsonPath().getInt("user.id");
        user.name = response.jsonPath().getString("user.name");
        user.email = response.jsonPath().getString("user.email");
        user.avatar = response.jsonPath().get("user.avatar");
        user.admin = response.jsonPath().getBoolean("user.admin");

        LoginResult loginResult = new LoginResult(accessToken, user);

        // Открываем браузер и вставляем токен в localStorage
        Selenide.open(BrowserHooks.BASE_URL);
        setLocalStorageWithAuth(loginResult);
        Selenide.refresh();
    }

    private void setLocalStorageWithAuth(LoginResult result) {
        ObjectMapper mapper = new ObjectMapper();
        String userJson;
        try {
            userJson = mapper.writeValueAsString(result.getUser());
        } catch (Exception e) {
            throw new RuntimeException("Не удалось сериализовать пользователя для localStorage", e);
        }

        Selenide.executeJavaScript(
                "window.localStorage.clear();" +
                        "window.localStorage.setItem('islogin', 'true');" +
                        "window.localStorage.setItem('token', arguments[0]);" +
                        "window.localStorage.setItem('user', arguments[1]);",
                result.getAccessToken(),
                userJson
        );
    }

    @When("Нажимаем \"Вход\" на главной странице")
    public void clickLoginButton() {
        homePage.clickLoginButton();
    }

    @When("Нажимаем кнопку \"Нет аккаунта?\" во всплывающем окне")
    public void clickNoAccountButton() {
        popupPage.clickNoAccountButton();
    }

    @When("Заполняем форму регистрации email и паролем")
    public void fillRegistrationForm() {
        popupPage.fillRegForm(email, password);
    }

    @When("Заполняем форму логина email и паролем")
    public void fillLoginForm() {
        popupPage.fillLoginForm(email, password);
    }

    @When("Нажимаем кнопку \"Создать аккаунт\"")
    public void clickRegisterButton() {
        popupPage.clickRegButton();
    }

    @When("Нажимаем кнопку \"Войти\"")
    public void clickLoginButtonPopup() {
        popupPage.clickLoginButton();
    }

    @Then("На главной странице отображается кнопка \"Выйти\"")
    public void verifyLogoutButtonDisplayed() {
        homePage.logoutButtonDisplayed();
    }

    @Then("Отображается ошибка регистрации")
    public void verifyRegErrorDisplayed() {
        popupPage.regErrorDisplayed();
    }
}
