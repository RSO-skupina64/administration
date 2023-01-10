package com.rso.microservice.service;


import com.rso.microservice.entity.ProductShop;
import com.rso.microservice.entity.ProductShopHistory;
import com.rso.microservice.entity.User;
import com.rso.microservice.repository.ProductShopHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductShopHistoryService {
    public static final Logger log = LoggerFactory.getLogger(ProductShopHistoryService.class);

    final ProductShopHistoryRepository productShopHistoryRepository;
    final ProductShopService productShopService;

    public ProductShopHistoryService(ProductShopHistoryRepository productShopHistoryRepository,
                                     ProductShopService productShopService) {
        this.productShopHistoryRepository = productShopHistoryRepository;
        this.productShopService = productShopService;
    }

    public ProductShopHistory createProductShopHistory(ProductShopHistory productShopHistory, Long productShopId) {
        Optional<ProductShop> productShopOptional = productShopService.findById(productShopId);
        if (productShopOptional.isPresent()) {
            productShopHistory.setProductShop(productShopOptional.get());
        } else {
            return null;
        }

        return productShopHistoryRepository.save(productShopHistory);
    }

    public ProductShopHistory updateProductShopHistory(ProductShopHistory productShopHistory) {
        if (productShopHistoryRepository.existsById(productShopHistory.getId())) {
            // if product shop is not set, we need to find and set it
            if (productShopHistory.getProductShop() == null) {
                Optional<ProductShopHistory> productShopHistoryOptional = productShopHistoryRepository.findById(productShopHistory.getId());
                if (productShopHistoryOptional.isPresent()) {
                    productShopHistory.setProductShop(productShopHistoryOptional.get().getProductShop());
                } else {
                    return null;
                }
            }

            return productShopHistoryRepository.save(productShopHistory);
        }

        return null;
    }

    public void removeProductShopHistoryByProductId(Long productId) {
        productShopHistoryRepository.deleteByProductId(productId);
    }

}
