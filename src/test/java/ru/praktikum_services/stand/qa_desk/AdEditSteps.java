package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.praktikum_services.stand.qa_desk.api.DataGenerator;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class AdEditSteps {

    private final TestContext context;
    private final DataGenerator dataGenerator;
    private String newAdTitle;

    public AdEditSteps(TestContext context) {
        this.context = context;
        this.dataGenerator = new DataGenerator();
    }

    @Given("User is registered in the system for ad edit")
    public void userIsRegisteredForEdit() {
        context.userRegisterData = dataGenerator.createUser();
        context.userApi.registerUser(context.userRegisterData);
    }

    @Given("User is authorized in the system for ad edit")
    public void userIsAuthorizedForAdEdit() {
        context.loginPage.openPage();
        context.homePageAfterLogin = context.loginPage.loginUser(context.userRegisterData);
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
    }

    @Given("User has a created advertisement")
    public void userHasAd() {
        context.adCreateData = dataGenerator.createAdForEdit();
        context.createAdPage = context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.shouldBeOpened();
        context.homePageAfterLogin = context.createAdPage.createAd(context.adCreateData);
        Selenide.sleep(3000);

        context.createdAdTitle = context.adCreateData.getName();
        verifyAdCreatedSuccessfully();
    }

    @When("User finds and opens advertisement from search for editing")
    public void user_finds_and_opens_advertisement_from_search_for_editing() {
        context.homePageAfterLogin.openPage();
        Selenide.sleep(2000);
        searchAdByTitle(context.createdAdTitle);
        setSearchCategory("Авто");
        setSearchCity("Москва");
        clickApplyButton();
        Selenide.sleep(3000);
        clickOnFoundAd();
        Selenide.sleep(2000);
    }

    @When("User clicks edit button in ad view")
    public void user_clicks_edit_button_in_ad_view() {
        SelenideElement editButton = $x("//button[contains(text(), 'Редактировать объявление')]");
        editButton.shouldBe(visible, enabled).click();
        Selenide.sleep(2000);
    }

    @When("User verifies edit page is opened")
    public void user_verifies_edit_page_is_opened() {
        SelenideElement editTitle = $(".createListing_title__IFtFs");
        editTitle.shouldBe(visible).shouldHave(text("Редактировать объявление"));

        SelenideElement nameInput = $("input[placeholder='Название']");
        nameInput.shouldBe(visible).shouldHave(value(context.createdAdTitle));
    }

    @When("User updates advertisement title")
    public void user_updates_advertisement_title() {
        newAdTitle = dataGenerator.generateNewProductName();
        SelenideElement nameInput = $("input[placeholder='Название']");
        nameInput.shouldBe(visible, editable).clear();
        nameInput.setValue(newAdTitle);
        Selenide.sleep(1000);
    }

    @When("User saves changes")
    public void user_saves_changes() {
        SelenideElement saveButton = $x("//button[contains(text(), 'Сохранить изменения')]");
        saveButton.shouldBe(visible, enabled).click();
        Selenide.sleep(3000);
    }

    @Then("Updated advertisement is found in search")
    public void updated_advertisement_is_found_in_search() {
        context.homePageAfterLogin.openPage();
        Selenide.sleep(2000);
        searchAdByTitle(newAdTitle);
        setSearchCategory("Авто");
        setSearchCity("Москва");
        clickApplyButton();
        Selenide.sleep(3000);

        boolean isAdFound = checkIfAdFoundInSearchResults(newAdTitle);
        Assertions.assertTrue(isAdFound,
                "Обновленное объявление '" + newAdTitle + "' не найдено в поиске");
    }

    private void verifyAdCreatedSuccessfully() {
        context.profilePage = context.homePageAfterLogin.clickProfileButton();
        Selenide.sleep(2000);

        boolean adInProfile = context.profilePage.hasAdWithTitle(context.createdAdTitle);
        Assertions.assertTrue(adInProfile,
                "Объявление не создалось успешно - не найдено в профиле: " + context.createdAdTitle);
    }

    private void searchAdByTitle(String title) {
        SelenideElement searchInput = $("input[placeholder*='купить'], input[placeholder*='поиск']");
        searchInput.shouldBe(visible).clear();
        searchInput.setValue(title);
        Selenide.sleep(1000);
    }

    private void setSearchCategory(String category) {
        SelenideElement categoryDropdown = $("[class*='category'] button, .category-filter");
        if (categoryDropdown.exists()) {
            categoryDropdown.click();
            Selenide.sleep(1000);
            SelenideElement categoryOption = $x("//*[contains(text(), '" + category + "')]");
            categoryOption.shouldBe(visible).click();
        }
    }

    private void setSearchCity(String city) {
        SelenideElement cityDropdown = $("[class*='city'] button, .city-filter");
        if (cityDropdown.exists()) {
            cityDropdown.click();
            Selenide.sleep(1000);
            SelenideElement cityOption = $x("//*[contains(text(), '" + city + "')]");
            cityOption.shouldBe(visible).click();
        }
    }

    private void clickApplyButton() {
        try {
            SelenideElement applyButton = $x("//button[contains(text(), 'Применить')]");
            applyButton.shouldBe(visible, enabled).click();
        } catch (Exception e) {
            SelenideElement searchInput = $("input[placeholder*='купить'], input[placeholder*='поиск']");
            searchInput.pressEnter();
        }
    }

    private void clickOnFoundAd() {
        SelenideElement adElement = $x("//div[contains(@class, 'about')]//h2[contains(@class, 'h2')]");
        adElement.shouldBe(visible).click();
    }

    private boolean checkIfAdFoundInSearchResults(String title) {
        ElementsCollection adCards = $$(".card, [class*='card'], [class*='ad']");
        for (SelenideElement card : adCards) {
            if (card.exists() && card.isDisplayed() && card.getText().contains(title)) {
                return true;
            }
        }
        return false;
    }
}