package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import ru.praktikum_services.stand.qa_desk.api.DataGenerator;

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
        context.loginPage.loginUser(context.userRegisterData);
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
    }

    @Given("User has a created advertisement")
    public void userHasAd() {
        context.adCreateData = dataGenerator.createAd();
        context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.createAd(context.adCreateData);
        context.homePageAfterLogin.clickProfileButton();
        Assertions.assertTrue(context.profilePage.hasAds());
    }

    @When("User clicks edit button")
    public void userEditsAd() {
        Assertions.assertTrue(context.profilePage.hasEditButton());
        context.profilePage.clickEditButton();
        Assertions.assertTrue(context.editAdPage.hasTitle(context.adCreateData.getName()));
    }

    @Then("Ad create form is opened")
    public void openCreateAdForm() {
        context.createAdPage.openPage();
    }
}