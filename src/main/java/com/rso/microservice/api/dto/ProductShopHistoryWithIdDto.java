package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ProductShopHistoryWithIdDto {
    @JsonProperty("id_product_shop_history")
    @NotNull(message = "is required")
    private Long idProductShopHistory;

    @JsonProperty("date")
    @NotNull(message = "is required")
    private LocalDateTime date;

    @JsonProperty("price_EUR")
    @NotNull(message = "is required")
    private Long priceEUR;

    public Long getIdProductShopHistory() {
        return idProductShopHistory;
    }

    public void setIdProductShopHistory(Long idProductShopHistory) {
        this.idProductShopHistory = idProductShopHistory;
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
