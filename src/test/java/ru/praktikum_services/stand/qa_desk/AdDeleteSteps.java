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

public class AdDeleteSteps {

    private final TestContext context;
    private final DataGenerator dataGenerator;

    public AdDeleteSteps(TestContext context) {
        this.context = context;
        this.dataGenerator = new DataGenerator();
    }

    @Given("User is registered in the system for ad deletion")
    public void userIsRegisteredForDeletion() {
        context.userRegisterData = dataGenerator.createUser();
        context.userApi.registerUser(context.userRegisterData);
    }

    @Given("User is authorized in the system for ad deletion")
    public void userIsLoggedIn() {
        context.loginPage.openPage();
        context.homePageAfterLogin = context.loginPage.loginUser(context.userRegisterData);
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
    }

    @Given("User has a created advertisement for deletion")
    public void userHasAdForDeletion() {
        context.adCreateData = dataGenerator.createAdForDeletion();
        context.createAdPage = context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.shouldBeOpened();
        context.homePageAfterLogin = context.createAdPage.createAd(context.adCreateData);
        Selenide.sleep(3000);

        context.createdAdTitle = context.adCreateData.getName();
        verifyAdCreatedSuccessfully();
    }

    @When("User verifies advertisement is published in profile")
    public void verifyAdvertisementPublished() {
        context.profilePage = context.homePageAfterLogin.clickProfileButton();
        Selenide.sleep(2000);

        boolean hasAdWithCorrectTitle = context.profilePage.hasAdWithTitle(context.createdAdTitle);
        Assertions.assertTrue(hasAdWithCorrectTitle,
                "Объявление с заголовком '" + context.createdAdTitle + "' не отображается в профиле");
    }

    @When("User finds and opens advertisement from search")
    public void user_finds_and_opens_advertisement_from_search() {
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

    @When("User clicks delete button in ad view")
    public void user_clicks_delete_button_in_ad_view() {
        SelenideElement deleteButton = $x("//button[contains(text(), 'Удалить')]");
        deleteButton.shouldBe(visible, enabled).click();
        Selenide.sleep(3000);
    }

    @Then("Advertisement is not found in search")
    public void advertisementNotFoundInSearch() {
        context.homePageAfterLogin.openPage();
        Selenide.sleep(2000);
        searchAdByTitle(context.createdAdTitle);
        setSearchCategory("Авто");
        setSearchCity("Москва");
        clickApplyButton();
        Selenide.sleep(3000);

        boolean isAdFound = checkIfAdFoundInSearchResults();
        Assertions.assertFalse(isAdFound,
                "Объявление '" + context.createdAdTitle + "' все еще найдено в поиске");
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

    private boolean checkIfAdFoundInSearchResults() {
        ElementsCollection adCards = $$(".card, [class*='card'], [class*='ad']");
        for (SelenideElement card : adCards) {
            if (card.exists() && card.isDisplayed() && card.getText().contains(context.createdAdTitle)) {
                return true;
            }
        }
        return false;
    }
}