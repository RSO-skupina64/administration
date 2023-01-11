package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDetailsWithIdDto {

    @JsonProperty("id_user")
    @NotNull(message = "is required")
    private Long idUser;

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

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
}
