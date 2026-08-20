package ru.practicum.Final_project.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public class BrowserHooks {
    public static final String BASE_URL ="https://qa-desk.education-services.ru";

    @Before
    public void openBrowser() {
        Selenide.open(BASE_URL);
        Configuration.holdBrowserOpen = false;
    }

    @After
   public void tearDown() {
      clearBrowserLocalStorage();
   }
}
