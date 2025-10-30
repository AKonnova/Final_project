package ru.praktikum_services.stand.qa_desk.pages;

import com.codeborne.selenide.SelenideElement;
import ru.praktikum_services.stand.qa_desk.constants.Url;
import ru.praktikum_services.stand.qa_desk.components.Header;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.EDIT_TITLE_SELECTOR;

@Getter
public class EditAdPage {

    private final SelenideElement titleLabel = $(EDIT_TITLE_SELECTOR);

    private final Header header;

    public EditAdPage() {
        this.header = new Header();
    }

    public EditAdPage openPage() {
        open(Url.HOST + "/edit-listing");
        return this;
    }

    public boolean hasTitle(String expectedTitle) {
        titleLabel.shouldBe(visible).shouldHave(text(expectedTitle));
        return true;
    }
}
