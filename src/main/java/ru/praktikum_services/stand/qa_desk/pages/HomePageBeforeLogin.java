package ru.praktikum_services.stand.qa_desk.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.HOST;

public class HomePageBeforeLogin extends BasePage {

    private final SelenideElement loginRegisterButton = $x(LOGIN_REGISTER_BUTTON_XPATH);
    private final SelenideElement profileButton = $(PROFILE_BUTTON_SELECTOR);
    private final SelenideElement username = $(USERNAME_SELECTOR);
    private final SelenideElement logoutButton = $x(LOGOUT_BUTTON_XPATH);

//    @Override
//    public void openPage() {
//        open(HOST);
//    }

    public LoginPage clickLoginRegisterButton() {
        loginRegisterButton.click();
        LoginPage loginPage = page(LoginPage.class);
        return loginPage;
    }

    public boolean isNotAuthorized() {
        loginRegisterButton.shouldBe(visible);
        profileButton.shouldNotBe(visible);
        username.shouldNotBe(visible);
        logoutButton.shouldNotBe(visible);
        return true;
    }

}