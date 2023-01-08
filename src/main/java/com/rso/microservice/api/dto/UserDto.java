package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    @JsonProperty("email")
    @NotBlank(message = "is required.")
    @Email
    private String email;

    @JsonProperty("name")
    @NotBlank(message = "is required.")
    private String name;

    @JsonProperty("last_name")
    @NotBlank(message = "is required.")
    private String lastName;

    @JsonProperty("username")
    @NotBlank(message = "is required.")
    @Length(min = 5, max = 20)
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "is required.")
    @Length(min = 5, max = 20)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
