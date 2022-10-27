package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopIdDto {
    @JsonProperty("id_shop")
    private String idShop;

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }
}
