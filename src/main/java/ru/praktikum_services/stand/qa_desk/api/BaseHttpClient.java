package ru.praktikum_services.stand.qa_desk.api;

import ru.praktikum_services.stand.qa_desk.constants.Url;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;


public class BaseHttpClient {

    protected RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri(Url.getHost())
            .addHeader("Content-type", "application/json")
            .setRelaxedHTTPSValidation()
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new ErrorLoggingFilter())
            .build();

}