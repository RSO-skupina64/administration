package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ProductTypeWithIdDto {

    @JsonProperty("id_product_type")
    @NotBlank(message = "is required")
    private String idProductType;

    @JsonProperty("name")
    private String name;

    public String getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(String idProductType) {
        this.idProductType = idProductType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
