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

    @Given("User is registered in the system")
    public void userIsRegistered() {
        context.userRegisterData = context.dataGenerator.createUser();
        context.userApi.registerUser(context.userRegisterData);
    }

    @When("User opens login page")
    public void openLoginPage() {
        context.loginPage.openPage();
    }

    @When("Enters correct data and logs in")
    public void enterValidCredentialsAndLogin() {
        context.homePageAfterLogin = context.loginPage.loginUser(context.userRegisterData);
    }

    @Then("User is authorized")
    public void userLoggedIn() {
        Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
        System.out.println("✅ Пользователь успешно авторизован: " + context.homePageAfterLogin.getUsername());
    }
}