package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.api.UserRegisterData;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.HOST;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class RegistrationPage extends BasePage {

    private final SelenideElement emailInput = $x(EMAIL_INPUT_XPATH);
    private final SelenideElement passwordInput = $x(PASSWORD_INPUT_XPATH);
    private final SelenideElement submitPasswordInput = $x(SUBMIT_PASSWORD_XPATH);
    private final SelenideElement createAccountButton = $x(CREATE_ACCOUNT_BUTTON_XPATH);
    private final SelenideElement registrationError = $x(REGISTRATION_ERROR_XPATH);

    public RegistrationPage openViaUI() {
        open(HOST);

        $x("//button[contains(text(), 'Вход и регистрация')]")
                .shouldBe(visible, enabled)
                .click();

        $x("//button[contains(text(), 'Нет аккаунта')]")
                .shouldBe(visible, enabled)
                .click();

        emailInput.shouldBe(visible);
        return this;
    }

    @Override
    public void openPage() {
        open(HOST + "/regiatration");
    }

    public void setEmail(String email) {
        emailInput.shouldBe(visible, editable).setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.shouldBe(visible, editable).setValue(password);
    }

    public void setSubmitPassword(String submitPassword) {
        submitPasswordInput.shouldBe(visible, editable).setValue(submitPassword);
    }

    public void clickCreateAccountButton() {
        createAccountButton.shouldBe(visible, enabled).click();
    }

    public HomePageAfterLogin registerUser(UserRegisterData user) {
        fillInRegistrationData(user);
        return page(HomePageAfterLogin.class);
    }

    public RegistrationPage tryRegisterUser(UserRegisterData user) {
        fillInRegistrationData(user);
        return this;
    }

    public boolean isRegistrationFailed() {
        registrationError.shouldBe(visible);
        return true;
    }

    public RegistrationPage shouldBeFailedRegistration() {
        registrationError.shouldBe(visible);
        return this;
    }

    private void fillInRegistrationData(UserRegisterData user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setSubmitPassword(user.getSubmitPassword());
        clickCreateAccountButton();
    }
}