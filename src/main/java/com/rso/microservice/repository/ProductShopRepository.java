package com.rso.microservice.repository;

import com.rso.microservice.entity.ProductShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductShopRepository extends JpaRepository<ProductShop, Long> {
}
