package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.ShopDto;
import com.rso.microservice.api.dto.ShopsArrayResponseDto;
import com.rso.microservice.entity.Shop;
import com.rso.microservice.entity.Type;
import com.rso.microservice.grpc.Shops.ShopGrpc;
import com.rso.microservice.vao.ShopListVAO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    @Mapping(source = "shops", target = "shops")
    @Mapping(source = "count", target = "count")
    ShopsArrayResponseDto toModel(ShopListVAO shopListVAO);

    List<ShopDto> toModel(List<Shop> shops);

    ShopDto toModel(Shop shop);

    @Mapping(source = "name", target = ".")
    String toModel(Type type);

    List<ShopGrpc> toModelGrpc(List<Shop> shops);

    ShopGrpc toModelGrpc(Shop shop);

}
