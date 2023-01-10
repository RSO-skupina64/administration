package com.rso.microservice.api;

import com.rso.microservice.api.dto.*;
import com.rso.microservice.api.mapper.ProductShopHistoryMapper;
import com.rso.microservice.service.ProductShopHistoryService;
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
@RequestMapping("/product-shop/history")
@Tag(name = "Product Shop History")
public class ProductShopHistoryAPI {
    private static final Logger log = LoggerFactory.getLogger(ProductShopHistoryAPI.class);

    final ProductShopHistoryService productShopHistoryService;

    final ProductShopHistoryMapper productShopHistoryMapper;

    public ProductShopHistoryAPI(ProductShopHistoryService productShopHistoryService,
                                 ProductShopHistoryMapper productShopHistoryMapper) {
        this.productShopHistoryService = productShopHistoryService;
        this.productShopHistoryMapper = productShopHistoryMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates new product shop history",
            description = "Creates new product shop history")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Create new product shop history",
                    content = @Content(schema = @Schema(implementation = ProductShopHistoryWithIdDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<ProductShopHistoryWithIdDto> createProductShopHistory(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
            @Valid @RequestBody ProductShopHistoryDto productShopHistory) {
        // todo jwt validation
        log.info("createProductShopHistory: ENTRY");
        ProductShopHistoryWithIdDto productShopHistoryWithId = productShopHistoryMapper.toModel(
                productShopHistoryService.createProductShopHistory(
                        productShopHistoryMapper.toModel(productShopHistory), productShopHistory.getIdProductShop()));
        log.info("createProductShopHistory: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(productShopHistoryWithId);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletes product shop history",
            description = "Deletes product shop history")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete product",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> deleteProductShopHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                               @Valid @RequestBody ProductIdDto productId) {
        // todo jwt validation
        log.info("deleteProductShopHistory: ENTRY");
        productShopHistoryService.removeProductShopHistoryByProductId(productId.getIdProduct());
        log.info("deleteProductShopHistory: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("deleteProductShopHistory completed"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates product shop history",
            description = "Updates product shop history")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update successful"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<?> updateProductShopHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                      @Valid @RequestBody ProductShopHistoryWithIdDto productShopHistoryWithId) {
        // todo jwt validation
        log.info("updateProductShopHistory: ENTRY");
        productShopHistoryService.updateProductShopHistory(productShopHistoryMapper.toModel(productShopHistoryWithId));
        log.info("updateProductShopHistory: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("updateProductShopHistory completed"));
    }

}
