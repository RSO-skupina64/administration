package com.rso.microservice.service;


import com.rso.microservice.entity.Shop;
import com.rso.microservice.repository.ShopRepository;
import com.rso.microservice.vao.ShopListVAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    public static final Logger log = LoggerFactory.getLogger(ShopService.class);

    final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ShopListVAO getAllShops() {
        log.info("fetching all shops from DB");
        List<Shop> favoriteShops = shopRepository.findAll();
        int count = 0;
        if (favoriteShops != null)
            count = favoriteShops.size();
        log.info("returning {} shops", count);
        return new ShopListVAO(count, favoriteShops);
    }

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Shop updateShop(Shop shop) {
        if (shopRepository.existsById(shop.getId())) {
            return shopRepository.save(shop);
        }

        return null;
    }

    public void removeShop(Long id) {
        if (shopRepository.existsById(id)) {
            shopRepository.deleteById(id);
        }
    }

}
