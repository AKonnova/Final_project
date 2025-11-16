package ru.praktikum_services.stand.qa_desk.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.praktikum_services.stand.qa_desk.pages.EditAdPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class AdCard {
    private final SelenideElement card;
    private final SelenideElement titleElement;
    private final SelenideElement editButtonElement;
    private final SelenideElement deleteButtonElement;

    private final By titleSelector = By.cssSelector(".about .h2");
    private final By editButtonSelector = By.cssSelector(".editButton");
    private final By deleteButtonSelector = By.cssSelector(".deleteButton");

    public AdCard(SelenideElement cardElement) {
        this.card = cardElement;
        this.titleElement = card.$(titleSelector);
        this.editButtonElement = card.$(editButtonSelector);
        this.deleteButtonElement = card.$(deleteButtonSelector);
    }

    public String getTitle() {
        return titleElement.shouldBe(visible).getText();
    }

    public void clickEdit() {
        editButtonElement.shouldBe(visible, enabled).click();
    }

    public void clickDelete() {
        deleteButtonElement.shouldBe(visible, enabled).click();
    }

    public EditAdPage editAd() {
        clickEdit();
        sleep(1000);
        return page(EditAdPage.class);
    }

    public void deleteAd() {
        clickDelete();
        sleep(1000);
    }

    public AdCard shouldHaveEditButton() {
        if (!editButtonElement.exists()) {
            throw new AssertionError("В карточке объявления нет кнопки для редактирования");
        }
        editButtonElement.shouldBe(visible.because("Кнопка редактирования должна быть видна"));
        return this;
    }

    public AdCard shouldHaveDeleteButton() {
        if (!deleteButtonElement.exists()) {
            throw new AssertionError("В карточке объявления нет кнопки удаления");
        }
        deleteButtonElement.shouldBe(visible.because("Кнопка удаления должна быть видна"));
        return this;
    }

    public boolean hasTitle(String expectedTitle) {
        return titleElement.shouldBe(visible).getText().contains(expectedTitle);
    }
}