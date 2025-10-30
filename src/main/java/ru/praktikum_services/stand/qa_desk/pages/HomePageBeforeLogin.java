package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.components.Header;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class HomePageBeforeLogin extends BasePage {
    private Header header = new Header();

    public Header getHeader() {
        return header;
    }

    public LoginPage clickLoginRegisterButton() {
        return header.clickLoginAndRegisterButton();
    }

    public boolean isNotAuthorized() {
        return header.isNotAuthorized();
    }
}