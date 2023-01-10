package com.rso.microservice.repository;

import com.rso.microservice.entity.ProductShopHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductShopHistoryRepository extends JpaRepository<ProductShopHistory, Long> {

    @Modifying
    @Query(value = "DELETE FROM product_shop_history WHERE product_shop_id IN (SELECT id FROM product_shop WHERE product_id = :productId)", nativeQuery = true)
    void deleteByProductId(@Param("productId") Long productId);
}
