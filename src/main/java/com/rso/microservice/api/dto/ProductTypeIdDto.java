package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTypeIdDto {
    @JsonProperty("id_type")
    private String idType;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
}
