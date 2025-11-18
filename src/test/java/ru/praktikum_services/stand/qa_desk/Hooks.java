package ru.praktikum_services.stand.qa_desk;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.headless = false;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }

    @After
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.screenshot("final_state");
            Selenide.closeWebDriver();
        }
    }
}