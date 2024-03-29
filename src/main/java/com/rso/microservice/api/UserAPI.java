package com.rso.microservice.api;

import com.rso.microservice.api.dto.ErrorDto;
import com.rso.microservice.api.dto.MessageDto;
import com.rso.microservice.api.dto.UserDto;
import com.rso.microservice.api.dto.UserIdDto;
import com.rso.microservice.api.mapper.UserMapper;
import com.rso.microservice.service.AuthenticationService;
import com.rso.microservice.service.UserService;
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
@RequestMapping("/user")
@Tag(name = "User")
public class UserAPI {
    private static final Logger log = LoggerFactory.getLogger(UserAPI.class);

    final UserService userService;
    final UserMapper userMapper;
    final AuthenticationService authenticationService;

    public UserAPI(UserService userService, UserMapper userMapper, AuthenticationService authenticationService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletes user",
            description = "Deletes user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete user",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                 @Valid @RequestBody UserIdDto userId) {
        log.info("deleteUser ENTRY");
        if (!authenticationService.checkUserRoleWrapper(jwt, "Administrator")) {
            log.info("deleteUser EXIT");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Long id = Long.parseLong(userId.getIdUser());
        userService.removeUser(id);
        log.info("deleteUser EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("deleteUser completed"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates user",
            description = "Updates user")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update successful"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<?> updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                        @Valid @RequestBody UserDto user) {
        log.info("updateUser ENTRY");
        if (!authenticationService.checkUserRoleWrapper(jwt, "Administrator")) {
            log.info("updateUser EXIT");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Long userId = authenticationService.getUserProfileWrapper(jwt);
        userService.updateUser(userId, userMapper.toModel(user));
        log.info("updateUser EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("updateUser completed"));
    }

}
