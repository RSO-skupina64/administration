package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.ProductShopHistoryDto;
import com.rso.microservice.api.dto.ProductShopHistoryWithIdDto;
import com.rso.microservice.entity.ProductShopHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductShopHistoryMapper {

    ProductShopHistory toModel(ProductShopHistoryDto productShopHistory);

    @Mapping(source = "id", target = "idProductShopHistory")
    ProductShopHistoryWithIdDto toModel(ProductShopHistory productShopHistory);

    @Mapping(source = "idProductShopHistory", target = "id")
    ProductShopHistory toModel(ProductShopHistoryWithIdDto productShopWithId);
}
