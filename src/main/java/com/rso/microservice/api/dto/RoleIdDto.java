package com.rso.microservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class RoleIdDto {

    @JsonProperty("id_role")
    @NotNull(message = "is required")
    private Integer idRole;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
