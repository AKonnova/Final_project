package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.components.AdCard;
import ru.praktikum_services.stand.qa_desk.components.Header;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.*;
import static ru.praktikum_services.stand.qa_desk.constants.Endpoints.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class ProfilePage extends BasePage {

    private final ElementsCollection advertisementCards = $$(AD_SELECTOR);
    private Header header;

    public ProfilePage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public void openPage() {
        open(HOST + PROFILE_PAGE);
    }

    public List<AdCard> getAllAdvertisements() {
        return advertisementCards.stream()
                .map(AdCard::new)
                .collect(Collectors.toList());
    }

    public boolean hasAds() {
        return !advertisementCards.isEmpty();
    }

    public void shouldHaveAdvertisements() {
        advertisementCards.first().shouldBe(visible);
    }

    public int getAdCount() {
        return advertisementCards.size();
    }

    public void clickFirstAd() {
        advertisementCards.first().click();
    }

    public boolean hasEditButton() {
        if (advertisementCards.isEmpty()) {
            return false;
        }
        AdCard firstAd = new AdCard(advertisementCards.first());
        try {
            firstAd.shouldHaveEditButton();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickEditButton() {
        if (!advertisementCards.isEmpty()) {
            AdCard firstAd = new AdCard(advertisementCards.first());
            firstAd.clickEdit();
        }
    }
}