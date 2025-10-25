package ru.praktikum_services.stand.qa_desk;

import ru.praktikum_services.stand.qa_desk.api.AdCreateData;
import ru.praktikum_services.stand.qa_desk.api.UserApi;
import ru.praktikum_services.stand.qa_desk.api.UserRegisterData;
import ru.praktikum_services.stand.qa_desk.pages.*;

public class TestContext {

    public UserRegisterData userRegisterData;
    public AdCreateData adCreateData;

    public HomePageAfterLogin homePageAfterLogin = new HomePageAfterLogin();
    public HomePageBeforeLogin homePageBeforeLogin = new HomePageBeforeLogin();
    public LoginPage loginPage = new LoginPage();
    public ProfilePage profilePage = new ProfilePage();
    public RegistrationPage registrationPage = new RegistrationPage();
    public EditAdPage editAdPage = new EditAdPage();
    public CreateAdPage createAdPage = new CreateAdPage();
    public UserApi userApi = new UserApi();

}