package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.components.Header;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.HOST;

public class HomePageAfterLogin extends BasePage {
    private Header header = new Header();

    public Header getHeader() {
        return header;
    }

    public String getUsername() {
        return header.getUserName();
    }

    public ProfilePage clickProfileButton() {
        return header.clickProfileButton();
    }

    public CreateAdPage clickCreateAdButton() {
        return header.clickCreateAdButton();
    }

    public void clickLogoutButton() {
        header.clickLogoutButton();
    }

    public boolean isAuthorized() {
        return header.isAuthorized();
    }

    public void openPage() {
        open(HOST);
    }
}