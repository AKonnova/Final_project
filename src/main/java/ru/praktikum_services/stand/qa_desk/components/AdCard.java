package ru.praktikum_services.stand.qa_desk.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.praktikum_services.stand.qa_desk.pages.EditAdPage;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

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
        editButtonElement.shouldBe(visible).click();
    }

    public void clickDelete() {
        deleteButtonElement.shouldBe(visible).click();
        sleep(1000);
    }

    public EditAdPage editAd() {
        clickEdit();
        return page(EditAdPage.class);
    }

    public AdCard shouldHaveEditButton() {
        if (!editButtonElement.exists()) {
            throw new AssertionError("Edit button not found");
        }
        editButtonElement.shouldBe(visible);
        return this;
    }

    public AdCard shouldHaveDeleteButton() {
        if (!deleteButtonElement.exists()) {
            throw new AssertionError("Delete button not found");
        }
        deleteButtonElement.shouldBe(visible);
        return this;
    }
}