package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import ru.praktikum_services.stand.qa_desk.api.DataGenerator;
import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

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
        context.profilePage = context.homePageAfterLogin.clickProfileButton();
        Assertions.assertTrue(context.profilePage.hasAds());
    }

    @When("User finds advertisement in profile")
    public void userFindsAdvertisementInProfile() {
        context.profilePage = context.homePageAfterLogin.clickProfileButton();
        Assertions.assertTrue(context.profilePage.hasAds(), "Объявление не найдено в профиле");
    }

    @When("User opens advertisement for editing")
    public void userOpensAdvertisementForEditing() {
        context.profilePage.clickFirstAd();
        Selenide.sleep(2000);
    }

    @When("User clicks edit button")
    public void userClicksEditButton() {
        try {
            SelenideElement editButton = $x("//button[text()='Редактировать']");
            editButton.shouldBe(visible, enabled).click();
            Selenide.sleep(2000);
        } catch (Exception e) {
            throw new AssertionError("В объявлении нет кнопки \"Редактировать\"", e);
        }
    }

    @Then("Edit advertisement form is opened")
    public void editAdFormOpened() {
        boolean isOpened = context.editAdPage.isOpened();
        Assertions.assertTrue(isOpened, "Форма редактирования не открылась");
    }
}