package ru.praktikum_services.stand.qa_desk.api;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    private final Faker faker;

    public DataGenerator() {
        this.faker = new Faker(new Locale("en"));
    }

    public UserRegisterData createUser() {
        String email = generateEmail();
        String password = generatePassword();
        String submitPassword = password;
        return new UserRegisterData(email, password, submitPassword);
    }

    public AdCreateData createAd() {
        String productName = generateProductName();
        String type = "Хобби";
        String condition = "Новый";
        String city = "Санкт-Петербург";
        String description = generateDescription();
        int price = generatePrice();
        return new AdCreateData(productName, type, condition, city, description, price);
    }

    private String generateEmail() {
        return faker.internet().emailAddress();
    }

    private String generatePassword() {
        return faker.internet().password(8, 10, true, true, true);
    }

    private String generateProductName() {
        return faker.commerce().productName();
    }

    private String generateDescription() {
        return faker.lorem().paragraph(5);
    }

    private int generatePrice() {
        return faker.number().numberBetween(50, 100500);
    }

}