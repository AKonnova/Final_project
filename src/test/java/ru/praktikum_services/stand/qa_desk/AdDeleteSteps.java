package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.praktikum_services.stand.qa_desk.api.DataGenerator;

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
        context.loginPage.loginUser(context.userRegisterData);
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
    }

    @Given("User has a created advertisement for deletion")
    public void userHasAdForDeletion() {
        context.adCreateData = dataGenerator.createAd();
        context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.createAd(context.adCreateData);
        context.homePageAfterLogin.clickProfileButton();
        Assertions.assertTrue(context.profilePage.hasAds());
    }

    @When("User goes to profile with ads")
    public void goToProfile() {
        context.homePageAfterLogin.clickProfileButton();
        context.profilePage.hasAds();
    }

    @Then("Delete button is available")
    public void deleteButtonAvailable() {
        Assertions.assertTrue(context.profilePage.hasDeleteButton());
    }
}