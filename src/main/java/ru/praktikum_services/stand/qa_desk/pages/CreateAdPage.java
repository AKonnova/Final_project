package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.api.AdCreateData;
import ru.praktikum_services.stand.qa_desk.components.Header;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.*;
import static ru.praktikum_services.stand.qa_desk.constants.Endpoints.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class CreateAdPage extends BasePage {

    private final SelenideElement nameInput = $(NAME_INPUT_SELECTOR);
    private final SelenideElement descriptionInput = $(DESCRIPTION_SELECTOR);
    private final SelenideElement priceInput = $(PRICE_INPUT_SELECTOR);
    private final SelenideElement publishButton = $x(POST_BUTTON_XPATH);

    private final SelenideElement categoryDropdownButton = $(CATEGORY_DROPDOWN_SELECTOR);
    private final SelenideElement cityDropdownButton = $(CITY_DROPDOWN_SELECTOR);
    private final ElementsCollection dropdownOptions = $$(DROPDOWN_OPTIONS_SELECTOR);

    private Header header;

    public CreateAdPage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public void openPage() {
        open(HOST + CREATE_AD_PAGE);
    }

    public void setName(String name) {
        nameInput.setValue(name);
    }

    public void setDescription(String description) {
        descriptionInput.setValue(description);
    }

    public void setPrice(int price) {
        priceInput.setValue(String.valueOf(price));
    }

    public void clickPublishButton() {
        publishButton.click();
    }

    public void selectCategory(String category) {
        categoryDropdownButton.click();
        Selenide.sleep(1000);
        dropdownOptions.findBy(text(category)).click();
    }

    public void selectCity(String city) {
        cityDropdownButton.click();
        Selenide.sleep(1000);
        dropdownOptions.findBy(text(city)).click();
    }

    public void selectCondition(String condition) {
        if ("Новый".equals(condition)) {
            $(CONDITION_NEW_RADIO).click();
        } else if ("Б/У".equals(condition)) {
            $(CONDITION_USED_RADIO).click();
        }
    }

    public HomePageAfterLogin createAd(AdCreateData ad) {
        setName(ad.getName());
        selectCategory(ad.getType());
        selectCity(ad.getCity());
        selectCondition(ad.getCondition());
        setDescription(ad.getDescription());
        setPrice(ad.getPrice());
        clickPublishButton();
        HomePageAfterLogin homePage = page(HomePageAfterLogin.class);
        return homePage;
    }
}