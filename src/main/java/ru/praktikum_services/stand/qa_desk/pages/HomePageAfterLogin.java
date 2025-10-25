package ru.praktikum_services.stand.qa_desk.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.*;
import static com.codeborne.selenide.Condition.visible;

public class HomePageAfterLogin extends BasePage {

    private final SelenideElement profileButton = $(PROFILE_BUTTON_SELECTOR);
    private final SelenideElement username = $(USERNAME_SELECTOR);
    private final SelenideElement logoutButton = $x(LOGOUT_BUTTON_XPATH);
    private final SelenideElement createAdButton = $x(CREATE_AD_BUTTON_XPATH);

    CreateAdPage createAdPage = page(CreateAdPage.class);
    ProfilePage profilePage = page(ProfilePage.class);
    RegistrationPage registrationPage = page(RegistrationPage.class);

//    @Override
//    public void openPage() {
//        open(HOST);
//    }

    public String getUsername() {
        return username.getText();
    }

    public ProfilePage clickProfileButton() {
        profileButton.click();
        return profilePage;
    }

    public CreateAdPage clickCreateAdButton() {
        createAdButton.click();
        return createAdPage;
    }

    public RegistrationPage clickLogoutButton() {
        logoutButton.click();
        return registrationPage;
    }

    public boolean isAuthorized() {
        profileButton.shouldBe(visible);
        username.shouldBe(visible);
        logoutButton.shouldBe(visible);
        return true;
    }

}