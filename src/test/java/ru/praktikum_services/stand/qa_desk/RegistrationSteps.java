package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import static com.codeborne.selenide.Selenide.screenshot;

public class RegistrationSteps {
    private final TestContext context;

    public RegistrationSteps(TestContext context) {
        this.context = context;
    }

    @Given("New user data is generated")
    public void generateUserData() {
        context.userRegisterData = context.dataGenerator.createUser();
    }

    @Given("User already registered in system")
    public void userIsAlreadyRegistered() {
        context.userRegisterData = context.dataGenerator.createUser();
        try {
            context.userApi.registerUser(context.userRegisterData);
        } catch (Exception e) {
            registerViaUI();
        }
    }

    @When("User opens home page")
    public void openHomePage() {
        context.homePageBeforeLogin.openPage();
    }

    @When("User navigates to registration form")
    public void goToRegistrationForm() {
        context.loginPage = context.homePageBeforeLogin.clickLoginRegisterButton();
        context.registrationPage = context.loginPage.clickRegisterButton();
    }

    @When("User registers with valid data")
    public void registerCorrectData() {
        try {
            context.userApi.registerUser(context.userRegisterData);
            context.loginPage.openPage();
            context.homePageAfterLogin = context.loginPage.loginUser(context.userRegisterData);
        } catch (Exception e) {
            context.registrationPage.openViaUI();
            context.homePageAfterLogin = context.registrationPage.registerUser(context.userRegisterData);
        }
    }

    @When("User attempts registration with existing data")
    public void registerWithExistingData() {
        context.userApi.registerUser(context.userRegisterData);
        context.registrationPage = context.registrationPage.openViaUI();
        context.registrationPage.tryRegisterUser(context.userRegisterData);
    }

    @Then("Authorized user name displayed in header")
    public void usernameIsDisplayed() {
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
        String userName = context.homePageAfterLogin.getUsername();
        Assertions.assertNotNull(userName);
        Assertions.assertFalse(userName.isEmpty());
    }

    @Then("Registration error message displayed")
    public void registrationErrorDisplayed() {
        Assertions.assertTrue(context.registrationPage.isRegistrationFailed());
    }

    private void registerViaUI() {
        context.registrationPage.openViaUI();
        context.homePageAfterLogin = context.registrationPage.registerUser(context.userRegisterData);
    }
}