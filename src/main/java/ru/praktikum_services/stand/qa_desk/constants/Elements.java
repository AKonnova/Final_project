package ru.praktikum_services.stand.qa_desk.constants;

public class Elements {

    /// ad card
    public static final String TITLE_SELECTOR = ".about .h2";
    public static final String LOCATION_SELECTOR = ".about .h3";
    public static final String PRICE_SELECTOR = ".price .h2";
    public static final String EDIT_BUTTON_SELECTOR = ".editButton";
    public static final String DELETE_BUTTON_SELECTOR = ".deleteButton";

    /// home
    public static final String LOGIN_REGISTER_BUTTON_XPATH = "//button[text()='Вход и регистрация']";
    public static final String PROFILE_BUTTON_SELECTOR = "button.circleSmall";
    public static final String USERNAME_SELECTOR = ".profileText.name";
    public static final String LOGOUT_BUTTON_XPATH = "//button[text()='Выйти']";
    public static final String CREATE_AD_BUTTON_XPATH = "//button[text()='Разместить объявление']";

    /// create ad
    public static final String NAME_INPUT_SELECTOR = "input[name='name']";
    public static final String DESCRIPTION_SELECTOR = "textarea[name='description']";
    public static final String PRICE_INPUT_SELECTOR = "input[name='price']";
    public static final String POST_BUTTON_XPATH = "//button[text()='Опубликовать']";

    /// edit ad

    public static final String EDIT_TITLE_SELECTOR = ".createListing_title__IFtFs";

    /// login
    public static final String EMAIL_INPUT_SELECTOR = "input[name='email']";
    public static final String PASSWORD_INPUT_SELECTOR = "input[name='password']";
    public static final String LOGIN_BUTTON_XPATH = "//button[text()='Войти']";
    public static final String REGISTER_BUTTON_XPATH = "//button[text()='Нет аккаунта']";

    /// profile
    public static final String AD_SELECTOR = ".card";

    /// register
    public static final String EMAIL_INPUT_XPATH = "//input[@name='email']";
    public static final String PASSWORD_INPUT_XPATH = "//input[@name='password']";
    public static final String SUBMIT_PASSWORD_XPATH = "//input[@name='submitPassword']";
    public static final String CREATE_ACCOUNT_BUTTON_XPATH = "//button[text()='Создать аккаунт']";
    public static final String REGISTRATION_ERROR_XPATH = "//span[text()='Ошибка']";

}