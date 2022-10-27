package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ProductTypeWithIdDto {

    @JsonProperty("id_type")
    @NotBlank(message = "is required")
    private String idType;

    @JsonProperty("name")
    @NotBlank(message = "is required")
    private String name;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
