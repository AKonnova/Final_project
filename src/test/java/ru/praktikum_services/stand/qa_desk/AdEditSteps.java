package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import ru.praktikum_services.stand.qa_desk.api.DataGenerator;
import ru.praktikum_services.stand.qa_desk.components.AdCard;
import com.codeborne.selenide.Selenide;

public class AdEditSteps {

    private final TestContext context;
    private final DataGenerator dataGenerator;

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
        context.adCreateData = dataGenerator.createAd();
        context.createAdPage = context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.shouldBeOpened();
        context.homePageAfterLogin = context.createAdPage.createAd(context.adCreateData);
        Selenide.sleep(3000);

        context.createdAdTitle = context.adCreateData.getName();

        context.profilePage = context.homePageAfterLogin.clickProfileButton();
        Selenide.sleep(2000);

        context.profilePage.shouldHaveAdvertisements();
    }

    @When("User finds advertisement in profile")
    public void userFindsAdvertisementInProfile() {
        context.profilePage.openPage();
        Selenide.sleep(2000);

        context.profilePage.shouldHaveAdvertisements();
    }

    @When("User opens advertisement for editing")
    public void userOpensAdvertisementForEditing() {
        AdCard firstAd = context.profilePage.getFirstAd();
        firstAd.shouldHaveEditButton();
        firstAd.clickEdit();
        Selenide.sleep(2000);
    }

    @When("User clicks edit button")
    public void userClicksEditButton() {
        context.editAdPage.shouldBeEditForm();
    }

    @Then("Edit advertisement form is opened")
    public void editAdFormOpened() {
        context.editAdPage.shouldBeEditForm();
    }
}