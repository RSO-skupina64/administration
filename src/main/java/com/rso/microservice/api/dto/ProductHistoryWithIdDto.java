package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ProductHistoryWithIdDto {
    @JsonProperty("id_product_history")
    @NotBlank(message = "is required")
    private Long idProductHistory;

    @JsonProperty("date")
    @NotBlank(message = "is required")
    private LocalDateTime date;

    @JsonProperty("price_EUR")
    @NotBlank(message = "is required")
    private Long priceEUR;

    public Long getIdProductHistory() {
        return idProductHistory;
    }

    public void setIdProductHistory(Long idProductHistory) {
        this.idProductHistory = idProductHistory;
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
