package ru.praktikum_services.stand.qa_desk.constants;

public class Elements {

    /// Header elements
    public static final String LOGIN_REGISTER_BUTTON_XPATH = "//button[contains(text(), 'Вход и регистрация')]";
    public static final String PROFILE_BUTTON_SELECTOR = "button.circleSmall";
    public static final String USERNAME_SELECTOR = ".profileText.name";
    public static final String LOGOUT_BUTTON_XPATH = "//button[contains(text(), 'Выйти')]";
    public static final String CREATE_AD_BUTTON_XPATH = "//button[contains(text(), 'Разместить объявление')]";

    /// Login page
    public static final String EMAIL_INPUT_SELECTOR = "input[name='email']";
    public static final String PASSWORD_INPUT_SELECTOR = "input[name='password']";
    public static final String LOGIN_BUTTON_XPATH = "//button[contains(text(), 'Войти')]";
    public static final String REGISTER_BUTTON_XPATH = "//button[contains(text(), 'Нет аккаунта')]";

    /// Registration page
    public static final String EMAIL_INPUT_XPATH = "//input[@name='email']";
    public static final String PASSWORD_INPUT_XPATH = "//input[@name='password']";
    public static final String SUBMIT_PASSWORD_XPATH = "//input[@name='submitPassword']";
    public static final String CREATE_ACCOUNT_BUTTON_XPATH = "//button[contains(text(), 'Создать аккаунт')]";
    public static final String REGISTRATION_ERROR_XPATH = "//span[contains(text(), 'Ошибка')]";

    /// Create ad page
    public static final String NAME_INPUT_SELECTOR = "input[name='name']";
    public static final String DESCRIPTION_SELECTOR = "textarea[name='description']";
    public static final String PRICE_INPUT_SELECTOR = "input[name='price']";
    public static final String POST_BUTTON_XPATH = "//button[contains(text(), 'Опубликовать')]";
    public static final String CATEGORY_DROPDOWN_SELECTOR = "input[name='category'] + button";
    public static final String CITY_DROPDOWN_SELECTOR = "input[name='city'] + button";
    public static final String CONDITION_NEW_RADIO = "input[value='Новый']";
    public static final String CONDITION_USED_RADIO = "input[value='Б/У']";

    /// Profile page
    public static final String AD_SELECTOR = ".card";
    public static final String EDIT_BUTTON_SELECTOR = ".editButton";
    public static final String DELETE_BUTTON_SELECTOR = ".deleteButton";

    /// Edit ad page
    public static final String EDIT_TITLE_SELECTOR = ".createListing_title__IFtFs";

    /// Common elements
    public static final String DROPDOWN_OPTIONS_SELECTOR = ".dropDownMenu_btn__o8ARs";
    public static final String SUCCESS_MESSAGE_SELECTOR = ".alert-success";
}