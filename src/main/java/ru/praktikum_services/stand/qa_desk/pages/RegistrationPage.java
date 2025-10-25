package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.api.UserRegisterData;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
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

    HomePageAfterLogin homePage = page(HomePageAfterLogin.class);

    @Override
    public void openPage() {
        open(HOST + REGISTER_PAGE);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
    }

    public HomePageAfterLogin registerUser(UserRegisterData user) {
        fillDataIn(user);
        clickCreateAccountButton();
        return homePage;
    }

    public HomePageAfterLogin registerExistingUser(UserRegisterData user) {
        registerUser(user);
        registerUser(user);
        return homePage;
    }

    public boolean isRegistrationFailed() {
        registrationError.shouldBe(visible);
        return true;
    }

    private void fillDataIn(UserRegisterData user) {
        $(emailInput).setValue(user.getEmail());
        $(passwordInput).setValue(user.getPassword());
        $(submitPasswordInput).setValue(user.getSubmitPassword());
    }

}