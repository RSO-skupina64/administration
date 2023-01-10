package com.rso.microservice.api;

import com.rso.microservice.api.dto.*;
import com.rso.microservice.api.mapper.ShopMapper;
import com.rso.microservice.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shop")
@Tag(name = "Shop")
public class ShopAPI {
    private static final Logger log = LoggerFactory.getLogger(ShopAPI.class);

    final ShopService shopService;

    final ShopMapper shopMapper;

    public ShopAPI(ShopService shopService, ShopMapper shopMapper) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates new Shop",
            description = "Creates new Shop")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Create new shop",
                    content = @Content(schema = @Schema(implementation = ShopWithIdDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<ShopWithIdDto> createShop(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                    @Valid @RequestBody ShopDto shop) {
        // todo jwt validation
        log.info("createShop ENTRY");
        ShopWithIdDto shopWithId = shopMapper.toModelShopWithIdDto(shopService.createShop(shopMapper.toModel(shop)));
        log.info("createShop EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(shopWithId);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletes shop",
            description = "Deletes shop")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete shop",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> deleteShop(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                 @Valid @RequestBody ShopIdDto shopId) {
        // todo jwt validation
        log.info("deleteShop ENTRY");
        Long id = Long.parseLong(shopId.getIdShop());
        shopService.removeShop(id);
        log.info("deleteShop EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("deleteShop completed"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates shop",
            description = "Updates shop")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update successful"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<?> updateShop(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                        @Valid @RequestBody ShopWithIdDto shopWithId) {
        // todo jwt validation
        log.info("updateShop ENTRY");
        shopService.updateShop(shopMapper.toModel(shopWithId));
        log.info("updateShop EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("updateShop completed"));
    }

    @GetMapping(value = "/multi", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all shops",
            description = "Get all shops")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of shops",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<ShopsArrayResponseDto> getShops() {
        log.info("getShops ENTRY");
        ResponseEntity<ShopsArrayResponseDto> response = ResponseEntity.status(HttpStatus.OK)
                .body(shopMapper.toModel(shopService.getAllShops()));
        log.info("getShops EXIT");
        return response;
    }

}
