package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {
    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("Registered user exists in system")
    public void registeredUserExists() {
        context.userRegisterData = context.dataGenerator.createUser();
        context.userApi.registerUser(context.userRegisterData);
    }

    @When("User opens login page")
    public void openLoginPage() {
        context.loginPage.openPage();
    }

    @When("User enters correct credentials and logs in")
    public void enterValidCredentialsAndLogin() {
        context.homePageAfterLogin = context.loginPage.loginUser(context.userRegisterData);
    }

    @Then("User is successfully authorized")
    public void userLoggedIn() {
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
        System.out.println("✅ Пользователь успешно авторизован: " + context.homePageAfterLogin.getUsername());
    }
}