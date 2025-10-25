package ru.praktikum_services.stand.qa_desk.api;

public class AdCreateData {

    private String name;
    private String type;
    private String condition;
    private String city;
    private String description;
    private int price;

    public AdCreateData(String name, String type, String condition, String city, String description, int price) {
        this.name = name;
        this.type = type;
        this.condition = condition;
        this.city = city;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}