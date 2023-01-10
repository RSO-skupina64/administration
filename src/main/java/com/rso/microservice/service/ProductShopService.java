package com.rso.microservice.service;

import com.rso.microservice.entity.ProductShop;
import com.rso.microservice.repository.ProductShopRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductShopService {

	private ProductShopRepository productShopRepository;

	public ProductShopService(ProductShopRepository productShopRepository) {
		this.productShopRepository = productShopRepository;
	}

	public Optional<ProductShop> findById(Long id) {
		return productShopRepository.findById(id);
	}

}
