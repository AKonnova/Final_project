package ru.praktikum_services.stand.qa_desk.pages;

import ru.praktikum_services.stand.qa_desk.api.AdCreateData;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static ru.praktikum_services.stand.qa_desk.constants.Url.*;
import static ru.praktikum_services.stand.qa_desk.constants.Endpoints.*;
import static ru.praktikum_services.stand.qa_desk.constants.Elements.*;

public class CreateAdPage extends BasePage {

    private final SelenideElement nameInput = $(NAME_INPUT_SELECTOR);
    private final SelenideElement descriptionInput = $(DESCRIPTION_SELECTOR);
    private final SelenideElement priceInput = $(PRICE_INPUT_SELECTOR);
    private final SelenideElement publishButton = $x(POST_BUTTON_XPATH);

    @Override
    public void openPage() {
        open(HOST + CREATE_AD_PAGE);
    }

    public void clickPublishButton() {
        publishButton.click();
    }

    public HomePageAfterLogin createAd(AdCreateData ad) {
        $(nameInput).setValue(ad.getName());
        $(descriptionInput).setValue(ad.getDescription());
        $(priceInput).setValue(String.valueOf(ad.getPrice()));
        clickPublishButton();
        HomePageAfterLogin homePage = page(HomePageAfterLogin.class);
        return homePage;
    }

}