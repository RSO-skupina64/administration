package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.ProductDto;
import com.rso.microservice.api.dto.ProductWithIdDto;
import com.rso.microservice.entity.Product;
import com.rso.microservice.entity.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toModel(ProductDto product);

    @Mapping(source = "id", target = "idProduct")
    ProductWithIdDto toModel(Product product);

    @Mapping(source = "idProduct", target = "id")
    Product toModel(ProductWithIdDto productWithId);

    ProductType toModel(String product);

    default String toModel(ProductType productType) {
        return productType.getName();
    }
}
