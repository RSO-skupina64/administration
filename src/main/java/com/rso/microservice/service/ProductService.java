package com.rso.microservice.service;


import com.rso.microservice.entity.Product;
import com.rso.microservice.entity.ProductShop;
import com.rso.microservice.entity.ProductShopHistory;
import com.rso.microservice.entity.ProductType;
import com.rso.microservice.repository.ProductRepository;
import com.rso.microservice.repository.ProductTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    public static final Logger log = LoggerFactory.getLogger(ProductService.class);

    final ProductRepository productRepository;
    final ProductTypeRepository productTypeRepository;

    public ProductService(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

    public Product createProduct(Product product, Long productTypeId) {
        Optional<ProductType> productTypeOptional = productTypeRepository.findById(productTypeId);
        if (productTypeOptional.isPresent()) {
            product.setProductType(productTypeOptional.get());
        } else {
            return null;
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Product product, Long productTypeId) {
        if (productRepository.existsById(product.getId())) {
            // if the product type id is set, we need to find it and set the product type on the product
            if (productTypeId != null) {
                Optional<ProductType> productTypeOptional = productTypeRepository.findById(productTypeId);
                if (productTypeOptional.isPresent()) {
                    product.setProductType(productTypeOptional.get());
                } else {
                    return null;
                }
            }

            return productRepository.save(product);
        }

        return null;
    }

    public void removeProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

}
