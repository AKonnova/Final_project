package ru.praktikum_services.stand.qa_desk.pages;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.open;
import static ru.praktikum_services.stand.qa_desk.constants.Url.HOST;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public abstract class BasePage {

    public void openPage() {
        open(HOST);
        Selenide.sleep(2000);
    }

}