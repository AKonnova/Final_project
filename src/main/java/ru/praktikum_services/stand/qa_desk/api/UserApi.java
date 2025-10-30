package ru.praktikum_services.stand.qa_desk.api;

import ru.praktikum_services.stand.qa_desk.constants.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseHttpClient {

    @Step("Register user")
    public Response registerUser(UserRegisterData userRegisterData) {
        System.out.println("Регистрация пользователя через API: " + userRegisterData.getEmail());
        return given()
                .spec(baseRequestSpec)
                .body(userRegisterData)
                .when()
                .post(Endpoints.API_SIGNUP)
                .then().log().all()
                .extract().response();
    }

    @Step("Login user")
    public Response loginUser(UserRegisterData userRegisterData) {
        System.out.println("Логин пользователя через API: " + userRegisterData.getEmail());
        return given()
                .spec(baseRequestSpec)
                .body(userRegisterData)
                .when()
                .post(Endpoints.API_LOGIN)
                .then().log().all()
                .extract().response();
    }

    @Step("Get access token from user")
    public String getToken(UserRegisterData userRegisterData) {
        Response response = loginUser(userRegisterData);
        String accessToken = response.then().extract().body().path("accessToken");
        System.out.println("Извлеченный токен: " + accessToken);
        return accessToken;
    }
}