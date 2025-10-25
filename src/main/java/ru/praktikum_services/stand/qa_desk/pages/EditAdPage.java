package ru.praktikum_services.stand.qa_desk.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.praktikum_services.stand.qa_desk.constants.Url.*;
import static ru.praktikum_services.stand.qa_desk.constants.Endpoints.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class EditAdPage extends BasePage {

    private final SelenideElement title = $(EDIT_TITLE_SELECTOR);

    @Override
    public void openPage() {
        open(HOST + EDIT_AD_PAGE);
    }

    public boolean hasTitle(String someTitle) {
        title.shouldBe(visible).shouldHave(text(someTitle));
        return true;
    }

}
