package ru.praktikum_services.stand.qa_desk.pages;

import com.codeborne.selenide.SelenideElement;
import ru.praktikum_services.stand.qa_desk.constants.Url;
import ru.praktikum_services.stand.qa_desk.components.Header;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.EDIT_TITLE_SELECTOR;

public class EditAdPage {

    private final SelenideElement titleLabel = $(EDIT_TITLE_SELECTOR);
    private final SelenideElement nameInput = $("input[name='name']");
    private final SelenideElement createAdTitle = $x("//h2[contains(text(), 'Создание объявления')]");

    private final Header header;

    public EditAdPage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public EditAdPage openPage() {
        open(Url.HOST + "/edit-listing");
        return this;
    }

    public boolean hasTitle(String expectedTitle) {
        titleLabel.shouldBe(visible).shouldHave(text(expectedTitle));
        return true;
    }

    public SelenideElement getTitleLabel() {
        return titleLabel.shouldBe(visible);
    }

    public boolean isOpened() {
        shouldBeEditForm();
        return true;
    }

    public EditAdPage shouldBeEditForm() {
        if (isCreateFormOpened()) {
            throw new AssertionError("Открыта форма создания объявления, вместо редактирования");
        }
        nameInput.shouldBe(visible.because("Форма редактирования должна содержать поле названия"));
        return this;
    }

    private boolean isCreateFormOpened() {
        try {
            if (createAdTitle.exists() && createAdTitle.isDisplayed()) {
                return true;
            }

            String nameValue = nameInput.getValue();
            if (nameValue == null || nameValue.isEmpty()) {
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}