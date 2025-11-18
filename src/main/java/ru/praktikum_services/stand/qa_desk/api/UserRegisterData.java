package ru.praktikum_services.stand.qa_desk.api;


public class UserRegisterData {

    private String email;
    private String password;
    private String submitPassword;

    public UserRegisterData(String email, String password, String submitPassword) {
        this.email = email;
        this.password = password;
        this.submitPassword = submitPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubmitPassword() {
        return submitPassword;
    }

    public void setSubmitPassword(String submitPassword) {
        this.submitPassword = submitPassword;
    }

}