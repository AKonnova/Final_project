package ru.praktikum_services.stand.qa_desk;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.headless = false;
    }
}