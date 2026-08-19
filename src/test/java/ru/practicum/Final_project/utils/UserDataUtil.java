package ru.practicum.Final_project.utils;


public class UserDataUtil {
    private static final String PASSWORD = "password";

    public static String uniqueEmail() {
        return "ernesto_" + System.currentTimeMillis() + "@yandex.ru";
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
