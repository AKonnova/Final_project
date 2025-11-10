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
        context.adCreateData = dataGenerator.createAd();
        context.createAdPage = context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.shouldBeOpened();
        context.homePageAfterLogin = context.createAdPage.createAd(context.adCreateData);
        Selenide.sleep(3000);

        context.createdAdTitle = context.adCreateData.getName();
    }

    @When("User verifies advertisement is published in profile")
    public void verifyAdvertisementPublished() {
        context.profilePage = context.homePageAfterLogin.clickProfileButton();
        Selenide.sleep(2000);

        boolean hasAds = context.profilePage.hasAds();
        Assertions.assertTrue(hasAds, "Объявление не отображается в профиле");
    }

    @When("User opens advertisement for viewing from profile")
    public void openAdvertisementFromProfile() {
        context.profilePage.clickFirstAd();
        Selenide.sleep(2000);
    }

    @When("User clicks delete button")
    public void clickDeleteButton() {
        try {
            SelenideElement deleteButton = $x("//button[text()='Удалить']");
            deleteButton.shouldBe(visible, enabled).click();
            Selenide.sleep(3000);
        } catch (Exception e) {
            throw new AssertionError("В объявлении нет кнопки \"Удалить\"", e);
        }
    }

    @Then("Advertisement is not found in search")
    public void advertisementNotFoundInSearch() {
        context.homePageAfterLogin.openPage();
        Selenide.sleep(2000);

        SelenideElement searchInput = $("input[placeholder='Я хочу купить...']");
        searchInput.shouldBe(visible).setValue(context.createdAdTitle);
        searchInput.pressEnter();
        Selenide.sleep(2000);

        boolean isAdFound = checkIfAdFoundInSearchResults();
        Assertions.assertFalse(isAdFound, "Объявление найдено в поиске: " + context.createdAdTitle);
    }

    private boolean checkIfAdFoundInSearchResults() {
        ElementsCollection adCards = $$(".card");

        for (SelenideElement card : adCards) {
            String cardText = card.getText();
            if (cardText.contains(context.createdAdTitle)) {
                return true;
            }
        }

        return false;
    }
}