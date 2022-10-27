package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ProductHistoryDto {

    @JsonProperty("id_product_shop")
    @NotBlank(message = "is required")
    private Long idProductShop;

    @JsonProperty("date")
    @NotBlank(message = "is required")
    private LocalDateTime date;

    @JsonProperty("price_EUR")
    @NotBlank(message = "is required")
    private Long priceEUR;

    public Long getIdProductShop() {
        return idProductShop;
    }

    public void setIdProductShop(Long idProductShop) {
        this.idProductShop = idProductShop;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getPriceEUR() {
        return priceEUR;
    }

    public void setPriceEUR(Long priceEUR) {
        this.priceEUR = priceEUR;
    }
}
