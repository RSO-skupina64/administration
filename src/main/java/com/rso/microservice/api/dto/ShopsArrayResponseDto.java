package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ShopsArrayResponseDto {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("shops")
    List<ShopDto> shops;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ShopDto> getShops() {
        return shops;
    }

    public void setShops(List<ShopDto> shops) {
        this.shops = shops;
    }
}
