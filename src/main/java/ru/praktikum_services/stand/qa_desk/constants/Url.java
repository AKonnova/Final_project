package ru.praktikum_services.stand.qa_desk.constants;


public class Url {

    public static final String HOST = "https://qa-desk.stand.praktikum-services.ru";

    public static String getHost() {
        if (System.getProperty("host") != null) {
            return System.getProperty("host");
        } else {
            return HOST;
        }
    }
}