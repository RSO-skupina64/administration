package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteProductDto {
    @JsonProperty("id_product")
    private Long idProduct;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

}
