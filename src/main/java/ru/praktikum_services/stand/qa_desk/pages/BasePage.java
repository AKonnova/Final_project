package ru.praktikum_services.stand.qa_desk.pages;

import static com.codeborne.selenide.Selenide.open;
import static ru.praktikum_services.stand.qa_desk.constants.Url.HOST;

public abstract class BasePage {

    public void openPage() {
        open(HOST);
    }

}