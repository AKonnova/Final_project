package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.api.UserRegisterData;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.*;
import static ru.praktikum_services.stand.qa_desk.constants.Endpoints.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class LoginPage extends BasePage {

    private final SelenideElement emailInput = $(EMAIL_INPUT_SELECTOR);
    private final SelenideElement passwordInput = $(PASSWORD_INPUT_SELECTOR);
    private final SelenideElement loginButton = $x(LOGIN_BUTTON_XPATH);
    private final SelenideElement registerButton = $x(REGISTER_BUTTON_XPATH);

    @Override
    public void openPage() {
        open(HOST + LOGIN_PAGE);
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public HomePageAfterLogin loginUser(UserRegisterData user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickLoginButton();
        HomePageAfterLogin homePage = page(HomePageAfterLogin.class);
        return homePage;
    }

    public RegistrationPage clickRegisterButton() {
        registerButton.click();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        return registrationPage;
    }
}