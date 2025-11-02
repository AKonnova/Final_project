package ru.praktikum_services.stand.qa_desk.api;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private final Faker faker;
    private final Random random;

    private static final List<String> CITIES = List.of(
            "Москва",
            "Санкт-Петербург",
            "Новосибирск",
            "Екатеринбург",
            "Нижний Новгород",
            "Казань"
    );

    private static final List<String> CATEGORIES = List.of(
            "Авто",
            "Книги",
            "Садоводство",
            "Хобби",
            "Технологии"
    );

    public DataGenerator() {
        this.faker = new Faker(new Locale("ru"));
        this.random = new Random();
    }

    public UserRegisterData createUser() {
        String email = "test_" + System.currentTimeMillis() + "@example.com";
        String password = "Password123!";
        String submitPassword = password;
        return new UserRegisterData(email, password, submitPassword);
    }

    public AdCreateData createAd() {
        String productName = generateProductName();
        String category = getRandomCategory();
        String condition = getRandomCondition();
        String city = getRandomCity();
        String description = generateDescription();
        int price = generatePrice();
        return new AdCreateData(productName, category, condition, city, description, price);
    }

    private String getRandomCategory() {
        return CATEGORIES.get(random.nextInt(CATEGORIES.size()));
    }

    private String getRandomCity() {
        return CITIES.get(random.nextInt(CITIES.size()));
    }

    private String getRandomCondition() {
        return random.nextBoolean() ? "Новый" : "Б/У";
    }

    private String generateProductName() {
        return faker.commerce().productName();
    }

    private String generateDescription() {
        return faker.lorem().sentence(10);
    }

    private int generatePrice() {
        return faker.number().numberBetween(100, 10000);
    }
}