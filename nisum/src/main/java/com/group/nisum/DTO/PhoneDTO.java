package com.group.nisum.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PhoneDTO {

    @JsonIgnore
    private Long id;
    @NotEmpty(message = "should not be empty")
    private Integer number;
    @NotEmpty(message = "should not be empty")
    private Integer cityCode;
    @NotEmpty(message = "should not be empty")
    private Integer countryCode;
    @JsonIgnore
    private Long user_id;

    public PhoneDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
