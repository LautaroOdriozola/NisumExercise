package com.group.nisum.DTO;

import javax.validation.constraints.NotEmpty;

public class RegexDTO {

    @NotEmpty(message = "should not be empty")
    String regex;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
