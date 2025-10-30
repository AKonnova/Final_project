package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.components.AdCard;
import ru.praktikum_services.stand.qa_desk.components.Header;
import com.codeborne.selenide.ElementsCollection;

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
        advertisementCards.first().shouldBe(visible);
        return true;
    }

    public boolean hasEditButton() {
        if (advertisementCards.isEmpty()) {
            return false;
        }
        AdCard firstAd = new AdCard(advertisementCards.first());
        firstAd.shouldHaveEditButton();
        return true;
    }

    public boolean hasDeleteButton() {
        if (advertisementCards.isEmpty()) {
            return false;
        }
        AdCard firstAd = new AdCard(advertisementCards.first());
        firstAd.shouldHaveDeleteButton();
        return true;
    }

    public void clickEditButton() {
        if (!advertisementCards.isEmpty()) {
            AdCard firstAd = new AdCard(advertisementCards.first());
            firstAd.clickEdit();
        }
    }

    public void clickDeleteButton() {
        if (!advertisementCards.isEmpty()) {
            AdCard firstAd = new AdCard(advertisementCards.first());
            firstAd.clickDelete();
        }
    }
}