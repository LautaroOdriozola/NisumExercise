package com.group.nisum.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class UserDTO {

    private Long id;
    @NotEmpty(message = "should not be empty")
    private String name;
    @NotEmpty(message = "should not be empty")
    @Email(message = "is not Valid")
    private String email;
    @NotEmpty(message = "should not be empty")
    private String password;
    @NotEmpty(message = "should not be empty")
    private List<PhoneDTO> phones;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="America/Santiago")
    private Timestamp created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="America/Santiago")
    private Timestamp modified;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="America/Santiago")
    private Timestamp last_login;
    private String token;
    private Boolean isActive;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }
}
