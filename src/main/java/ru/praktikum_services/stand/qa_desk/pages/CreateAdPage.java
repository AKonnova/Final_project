package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.api.AdCreateData;
import ru.praktikum_services.stand.qa_desk.components.Header;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.HOST;
import static ru.praktikum_services.stand.qa_desk.constants.Endpoints.CREATE_AD_PAGE;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class CreateAdPage extends BasePage {

    private final SelenideElement nameInput = $(NAME_INPUT_SELECTOR);
    private final SelenideElement descriptionInput = $(DESCRIPTION_SELECTOR);
    private final SelenideElement priceInput = $(PRICE_INPUT_SELECTOR);
    private final SelenideElement publishButton = $x(POST_BUTTON_XPATH);

    private final SelenideElement categoryDropdownButton = $(CATEGORY_DROPDOWN_SELECTOR);
    private final SelenideElement cityDropdownButton = $(CITY_DROPDOWN_SELECTOR);
    private final ElementsCollection dropdownOptions = $$(DROPDOWN_OPTIONS_SELECTOR);

    private final SelenideElement newConditionRadioCircle = $x("//input[@value='Новый']/following-sibling::div[contains(@class, 'radioUnput_inputActive__eC-HY')]");
    private final SelenideElement usedConditionRadioCircle = $x("//input[@value='Б/У']/following-sibling::div[contains(@class, 'radioUnput_inputRegular__FbVbr')]");

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
        nameInput.shouldBe(visible, editable).setValue(name);
    }

    public void setDescription(String description) {
        descriptionInput.shouldBe(visible, editable).setValue(description);
    }

    public void setPrice(int price) {
        priceInput.shouldBe(visible, editable).setValue(String.valueOf(price));
    }

    public void clickPublishButton() {
        publishButton.shouldBe(visible, enabled).click();
    }

    public void selectCategory(String category) {
        categoryDropdownButton.shouldBe(visible, enabled).click();
        sleep(1000);
        dropdownOptions.findBy(text(category)).shouldBe(visible, enabled).click();
    }

    public void selectCity(String city) {
        cityDropdownButton.shouldBe(visible, enabled).click();
        sleep(1000);
        dropdownOptions.findBy(text(city)).shouldBe(visible, enabled).click();
    }

    public void selectCondition(String condition) {
        if ("Новый".equals(condition)) {
            newConditionRadioCircle.shouldBe(visible, enabled).click();
        } else if ("Б/У".equals(condition)) {
            usedConditionRadioCircle.shouldBe(visible, enabled).click();
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
        return page(HomePageAfterLogin.class);
    }

    public CreateAdPage shouldBeOpened() {
        nameInput.shouldBe(visible);
        publishButton.shouldBe(visible);
        return this;
    }

    public boolean isCreateFormOpened() {
        try {
            nameInput.shouldBe(visible.because("Поле названия должно быть видно в форме создания"));
            descriptionInput.shouldBe(visible.because("Поле описания должно быть видно в форме создания"));
            priceInput.shouldBe(visible.because("Поле цены должно быть видно в форме создания"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}