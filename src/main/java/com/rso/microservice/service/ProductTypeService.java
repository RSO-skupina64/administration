package com.rso.microservice.service;


import com.rso.microservice.entity.ProductType;
import com.rso.microservice.repository.ProductTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService {
    public static final Logger log = LoggerFactory.getLogger(ProductTypeService.class);

    final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public ProductType createProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public ProductType updateProductType(ProductType productType) {
        if (productTypeRepository.existsById(productType.getId())) {
            return productTypeRepository.save(productType);
        }

        return null;
    }

    public void removeProductType(Long id) {
        if (productTypeRepository.existsById(id)) {
            productTypeRepository.deleteById(id);
        }
    }

}
