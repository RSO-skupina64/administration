package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.RoleDto;
import com.rso.microservice.api.dto.RoleWithIdDto;
import com.rso.microservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toModel(RoleDto role);

    @Mapping(source = "id", target = "idRole")
    RoleWithIdDto toModel(Role role);

    @Mapping(source = "idRole", target = "id")
    Role toModel(RoleWithIdDto roleWithId);
}
