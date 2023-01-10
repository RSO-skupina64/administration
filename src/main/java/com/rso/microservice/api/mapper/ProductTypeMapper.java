package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.ProductTypeDto;
import com.rso.microservice.api.dto.ProductTypeWithIdDto;
import com.rso.microservice.entity.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    ProductType toModel(ProductTypeDto productType);

    @Mapping(source = "id", target = "idProductType")
    ProductTypeWithIdDto toModel(ProductType productType);

    @Mapping(source = "idProductType", target = "id")
    ProductType toModel(ProductTypeWithIdDto productTypeWithId);
}
