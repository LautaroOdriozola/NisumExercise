package com.group.nisum.utils;

public class PasswordRegexValidator {

    private static PasswordRegexValidator INSTANCE;

    public String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";


    private PasswordRegexValidator(){}

    public static PasswordRegexValidator getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new PasswordRegexValidator();
        }
        return INSTANCE;
    }

    public String getPasswordPattern() {
        return passwordPattern;
    }

    public void setPasswordPattern(String passwordPattern) {
        this.passwordPattern = passwordPattern;
    }

    public boolean isValid(final String password) {
        return password.matches(passwordPattern);
    }

}
