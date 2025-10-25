package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import ru.praktikum_services.stand.qa_desk.api.DataGenerator;

public class AdCreateSteps {

    private final TestContext context;
    private final DataGenerator dataGenerator;

    public AdCreateSteps(TestContext context) {
        this.context = context;
        this.dataGenerator = new DataGenerator();
    }

    @Given("User is authorized in the system for ad creation")
    public void userIsAuthorizedForAdCreation() {
        context.loginPage.openPage();
        context.loginPage.loginUser(context.userRegisterData);
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
    }

    @When("User creates new advertisement")
    public void createNewAd() {
        context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.createAd(dataGenerator.createAd());
    }

    @Then("Ad is displayed in user profile")
    public void adDisplayedInProfile() {
        context.homePageAfterLogin.clickProfileButton();
        context.profilePage.hasAds();
    }

}