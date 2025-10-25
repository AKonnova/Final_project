package ru.praktikum_services.stand.qa_desk.api;

import ru.praktikum_services.stand.qa_desk.constants.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseHttpClient {

    @Step("Register user")
    public Response registerUser(UserRegisterData userRegisterData) {
        return given()
                .spec(baseRequestSpec)
                .body(userRegisterData)
                .when()
                .post(Endpoints.API_SIGNUP);
    }

    @Step("Get access token from user")
    public String getToken(Response response) {
        return response
                .then()
                .extract()
                .body()
                .path("accessToken");
    }

}