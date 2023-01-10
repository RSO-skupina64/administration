package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTypeIdDto {
    @JsonProperty("id_product_type")
    private String idProductType;

    public String getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(String idProductType) {
        this.idProductType = idProductType;
    }
}
