package com.rso.microservice.api;

import com.rso.microservice.api.dto.*;
import com.rso.microservice.api.mapper.RoleMapper;
import com.rso.microservice.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@Tag(name = "Role")
public class RoleAPI {
    private static final Logger log = LoggerFactory.getLogger(RoleAPI.class);

    final RoleService roleService;

    final RoleMapper roleMapper;

    public RoleAPI(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates new user role",
            description = "Creates new user role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Creation successful",
                    content = @Content(schema = @Schema(implementation = RoleWithIdDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<RoleWithIdDto> createRole(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                    @Valid @RequestBody RoleDto role) {
        // todo jwt validation
        log.info("createRole ENTRY");
        RoleWithIdDto roleWithId = roleMapper.toModel(roleService.createRole(roleMapper.toModel(role)));
        log.info("createRole EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(roleWithId);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletes role",
            description = "Deletes role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete product type",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> deleteRole(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                 @Valid @RequestBody RoleIdDto roleId) {
        // todo jwt validation
        log.info("deleteRole ENTRY");
        Long id = Long.valueOf(roleId.getIdRole());
        roleService.removeRole(id);
        log.info("deleteRole EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("deleteRole completed"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates role",
            description = "Updates role")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update successful"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<?> updateRole(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                        @Valid @RequestBody RoleWithIdDto roleWithId) {
        // todo jwt validation
        log.info("updateRole ENTRY");
        roleService.updateRole(roleMapper.toModel(roleWithId));
        log.info("updateRole EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("updateRole completed"));
    }

}
