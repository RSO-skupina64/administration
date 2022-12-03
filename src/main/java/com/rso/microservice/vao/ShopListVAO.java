package com.rso.microservice.vao;


import com.rso.microservice.entity.Shop;

import java.util.List;

public class ShopListVAO {

    private final Integer count;
    private final List<Shop> shops;

    public ShopListVAO(int count, List<Shop> shops) {
        this.count = count;
        this.shops = shops;
    }

    public Integer getCount() {
        return count;
    }

    public List<Shop> getShops() {
        return shops;
    }
}
