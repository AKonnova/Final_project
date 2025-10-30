package ru.praktikum_services.stand.qa_desk.components;

import com.codeborne.selenide.SelenideElement;
import ru.praktikum_services.stand.qa_desk.pages.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class Header {

    private final SelenideElement loginAndRegisterButton = $x("//button[contains(text(), 'Вход')]");
    private final SelenideElement profileButton = $("button.circleSmall");
    private final SelenideElement userNameLabel = $(".profileText.name");
    private final SelenideElement logoutButton = $x("//button[contains(text(), 'Выйти')]");
    private final SelenideElement createAdButton = $x("//button[contains(text(), 'Разместить')]");

    public String getUserName() {
        return userNameLabel.shouldBe(visible).getText();
    }

    public LoginPage clickLoginAndRegisterButton() {
        loginAndRegisterButton.shouldBe(visible).click();
        return page(LoginPage.class);
    }

    public ProfilePage clickProfileButton() {
        profileButton.shouldBe(visible).click();
        return page(ProfilePage.class);
    }

    public CreateAdPage clickCreateAdButton() {
        createAdButton.shouldBe(visible).click();
        return page(CreateAdPage.class);
    }

    public void clickLogoutButton() {
        logoutButton.shouldBe(visible).click();
    }

    public boolean isAuthorized() {
        try {
            profileButton.shouldBe(visible, exist);
            userNameLabel.shouldBe(visible, exist);
            logoutButton.shouldBe(visible, exist);
            return true;
        } catch (Exception e) {
            System.out.println("User is not authorized: " + e.getMessage());
            return false;
        }
    }

    public boolean isNotAuthorized() {
        try {
            loginAndRegisterButton.shouldBe(visible, exist);
            return true;
        } catch (Exception e) {
            System.out.println("Unexpected state - login button not found: " + e.getMessage());
            return false;
        }
    }
}