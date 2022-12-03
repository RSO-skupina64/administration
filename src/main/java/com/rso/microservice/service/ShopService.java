package com.rso.microservice.service;


import com.rso.microservice.entity.Shop;
import com.rso.microservice.repository.ShopRepository;
import com.rso.microservice.vao.ShopListVAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ShopListVAO getAllShops() {
        List<Shop> favoriteShops = shopRepository.findAll();

        return new ShopListVAO(favoriteShops.size(), favoriteShops);
    }

}
