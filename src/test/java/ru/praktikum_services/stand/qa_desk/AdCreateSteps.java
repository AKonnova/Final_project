package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class AdCreateSteps {
    private final TestContext context;

    public AdCreateSteps(TestContext context) {
        this.context = context;
    }

    @Given("User is authorized in the system for ad creation")
    public void userIsAuthorizedForAdCreation() {
        context.loginPage.openPage();
        context.homePageAfterLogin = context.loginPage.loginUser(context.userRegisterData);
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
    }

    @When("User creates new advertisement")
    public void createNewAd() {
        context.adCreateData = context.dataGenerator.createAd();
        context.createAdPage = context.homePageAfterLogin.clickCreateAdButton();
        context.createAdPage.shouldBeOpened();
        context.homePageAfterLogin = context.createAdPage.createAd(context.adCreateData);
    }

    @Then("Ad is displayed in user profile")
    public void adDisplayedInProfile() {
        context.profilePage = context.homePageAfterLogin.clickProfileButton();
        Assertions.assertTrue(context.profilePage.hasAds());
    }
}