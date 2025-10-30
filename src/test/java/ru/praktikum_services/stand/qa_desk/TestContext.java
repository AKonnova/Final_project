package ru.praktikum_services.stand.qa_desk;

import ru.praktikum_services.stand.qa_desk.api.AdCreateData;
import ru.praktikum_services.stand.qa_desk.api.UserApi;
import ru.praktikum_services.stand.qa_desk.api.UserRegisterData;
import ru.praktikum_services.stand.qa_desk.api.DataGenerator;
import ru.praktikum_services.stand.qa_desk.pages.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestContext {
    public UserRegisterData userRegisterData;
    public AdCreateData adCreateData;
    public DataGenerator dataGenerator;

    public HomePageAfterLogin homePageAfterLogin;
    public HomePageBeforeLogin homePageBeforeLogin;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public RegistrationPage registrationPage;
    public EditAdPage editAdPage;
    public CreateAdPage createAdPage;
    public UserApi userApi;

    public TestContext() {
        setup();
        this.dataGenerator = new DataGenerator();
        this.homePageAfterLogin = new HomePageAfterLogin();
        this.homePageBeforeLogin = new HomePageBeforeLogin();
        this.loginPage = new LoginPage();
        this.profilePage = new ProfilePage();
        this.registrationPage = new RegistrationPage();
        this.editAdPage = new EditAdPage();
        this.createAdPage = new CreateAdPage();
        this.userApi = new UserApi();
    }

    private void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    public void cleanup() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}