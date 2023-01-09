package com.rso.microservice.api.mapper;

import com.rso.microservice.api.dto.UserDto;
import com.rso.microservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserDto user);
}
