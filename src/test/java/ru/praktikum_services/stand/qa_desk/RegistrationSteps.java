package ru.praktikum_services.stand.qa_desk;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import ru.praktikum_services.stand.qa_desk.api.UserApi;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationSteps {
    private final TestContext context;

    public RegistrationSteps(TestContext context) {
        this.context = context;
    }

    @Given("User data is generated")
    public void generateUserData() {
        context.userRegisterData = context.dataGenerator.createUser();
        System.out.println("Сгенерирован пользователь: " + context.userRegisterData.getEmail());
    }

    @Given("User is already registered in the system")
    public void userIsAlreadyRegistered() {
        context.userRegisterData = context.dataGenerator.createUser();
        try {
            context.userApi.registerUser(context.userRegisterData);
            System.out.println("✅ API регистрация успешна: " + context.userRegisterData.getEmail());
        } catch (Exception e) {
            System.out.println("❌ API регистрация не удалась: " + e.getMessage());
            registerViaUI();
        }
    }

    @When("User opens home page")
    public void openHomePage() {
        context.homePageBeforeLogin.openPage();
        System.out.println("Открыта главная страница");
    }

    @When("Goes to the registration form")
    public void goToRegistrationForm() {
        context.loginPage = context.homePageBeforeLogin.clickLoginRegisterButton();
        context.registrationPage = context.loginPage.clickRegisterButton();
        System.out.println("✅ Перешли на форму регистрации через UI flow");
    }

    @When("Registers with correct data")
    public void registerCorrectData() {
        try {
            context.userApi.registerUser(context.userRegisterData);
            System.out.println("✅ API регистрация успешна: " + context.userRegisterData.getEmail());

            context.loginPage.openPage();
            context.homePageAfterLogin = context.loginPage.loginUser(context.userRegisterData);

        } catch (Exception e) {
            System.out.println("❌ API регистрация не удалась, используем UI: " + e.getMessage());

            context.registrationPage.openViaUI();
            context.homePageAfterLogin = context.registrationPage.registerUser(context.userRegisterData);
        }
    }

    @When("Registers with already existing data")
    public void registerWithExistingData() {
        System.out.println("1. Создаем пользователя через API: " + context.userRegisterData.getEmail());
        try {
            context.userApi.registerUser(context.userRegisterData);
            System.out.println("✅ Пользователь создан через API");
        } catch (Exception e) {
            System.out.println("❌ Ошибка при создании пользователя через API: " + e.getMessage());
            throw e;
        }

        System.out.println("⏳ Ждем 1 секунду для стабилизации");
        sleep(1000);

        System.out.println("2. Пытаемся зарегистрировать того же пользователя через UI");

        context.registrationPage = context.registrationPage.openViaUI();

        System.out.println("📝 Заполняем форму регистрации");
        context.registrationPage.tryRegisterUser(context.userRegisterData);
        System.out.println("3. Форма отправлена, ожидаем ошибку");
    }

    @Then("Authorized user username is displayed in header")
    public void usernameIsDisplayed() {
        sleep(1000);
        try {
            Assertions.assertTrue(context.homePageAfterLogin.isAuthorized());
            String userName = context.homePageAfterLogin.getUsername();
            System.out.println("Успешная авторизация. Имя пользователя: " + userName);
        } catch (Exception e) {
            System.out.println("Ошибка авторизации: " + e.getMessage());
            System.out.println("Текущий URL: " + webdriver().driver().url());
            screenshot("auth_failed");
            throw e;
        }
    }

    @Then("Registration error is displayed")
    public void registrationErrorDisplayed() {
        sleep(1000);
        System.out.println("Проверяем ошибку регистрации. Текущий URL: " + webdriver().driver().url());
        try {
            Assertions.assertTrue(context.registrationPage.isRegistrationFailed());
            System.out.println("✅ Ошибка регистрации отображается корректно");
        } catch (Exception e) {
            System.out.println("❌ Ошибка регистрации не отображается: " + e.getMessage());
            screenshot("registration_error_failed");
            throw e;
        }
    }

    private void registerViaUI() {
        context.registrationPage.openViaUI();
        context.homePageAfterLogin = context.registrationPage.registerUser(context.userRegisterData);
        System.out.println("Пользователь зарегистрирован через UI: " + context.userRegisterData.getEmail());
    }
}