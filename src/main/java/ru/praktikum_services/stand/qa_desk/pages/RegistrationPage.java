package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.api.UserRegisterData;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.*;
import static ru.praktikum_services.stand.qa_desk.constants.Endpoints.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class RegistrationPage extends BasePage {

    private final SelenideElement emailInput = $x(EMAIL_INPUT_XPATH);
    private final SelenideElement passwordInput = $x(PASSWORD_INPUT_XPATH);
    private final SelenideElement submitPasswordInput = $x(SUBMIT_PASSWORD_XPATH);
    private final SelenideElement createAccountButton = $x(CREATE_ACCOUNT_BUTTON_XPATH);
    private final SelenideElement registrationError = $x(REGISTRATION_ERROR_XPATH);

    public RegistrationPage openViaUI() {
        System.out.println("🔄 Открываем форму регистрации через UI");
        open(HOST);
        System.out.println("✅ Открыта главная страница");

        System.out.println("Нажимаем 'Вход и регистрация'");
        $x("//button[contains(text(), 'Вход и регистрация')]")
                .shouldBe(visible, enabled)
                .click();

        System.out.println("Нажимаем 'Нет аккаунта'");
        $x("//button[contains(text(), 'Нет аккаунта')]")
                .shouldBe(visible, enabled)
                .click();

        System.out.println("Ждем загрузки формы регистрации");
        emailInput.shouldBe(visible);
        System.out.println("✅ Форма регистрации загружена");

        return this;
    }

    @Override
    public void openPage() {
        open(HOST + REGISTER_PAGE);
    }

    public void setEmail(String email) {
        System.out.println("✏️ Вводим email: " + email);
        emailInput.shouldBe(visible, editable).setValue(email);
    }

    public void setPassword(String password) {
        System.out.println("✏️ Вводим пароль: " + password);
        passwordInput.shouldBe(visible, editable).setValue(password);
    }

    public void setSubmitPassword(String submitPassword) {
        System.out.println("✏️ Подтверждаем пароль: " + submitPassword);
        submitPasswordInput.shouldBe(visible, editable).setValue(submitPassword);
    }

    public void clickCreateAccountButton() {
        System.out.println("🖱️ Нажимаем кнопку 'Создать аккаунт'");
        createAccountButton.shouldBe(visible, enabled).click();
    }

    public HomePageAfterLogin registerUser(UserRegisterData user) {
        fillInRegistrationData(user);
        sleep(2000);
        return page(HomePageAfterLogin.class);
    }

    public RegistrationPage tryRegisterUser(UserRegisterData user) {
        System.out.println("🔄 Пытаемся зарегистрировать юзера: " + user.getEmail());
        fillInRegistrationData(user);
        return this;
    }

    public boolean isRegistrationFailed() {
        System.out.println("🔍 Проверяем наличие ошибки регистрации");
        registrationError.shouldBe(visible);
        System.out.println("✅ Ошибка регистрации отображена");
        return true;
    }

    private void fillInRegistrationData(UserRegisterData user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setSubmitPassword(user.getSubmitPassword());
        clickCreateAccountButton();
    }
}