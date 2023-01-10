package com.rso.microservice.api;

import com.rso.microservice.api.dto.*;
import com.rso.microservice.api.mapper.ProductTypeMapper;
import com.rso.microservice.service.ProductTypeService;
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
@RequestMapping("/product-type")
@Tag(name = "Product Type")
public class ProductTypeAPI {
    private static final Logger log = LoggerFactory.getLogger(ProductTypeAPI.class);

    final ProductTypeService productTypeService;

    final ProductTypeMapper productTypeMapper;

    public ProductTypeAPI(ProductTypeService productTypeService, ProductTypeMapper productTypeMapper) {
        this.productTypeService = productTypeService;
        this.productTypeMapper = productTypeMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates new Type",
            description = "Creates new Type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Creation successful",
                    content = @Content(schema = @Schema(implementation = ProductTypeWithIdDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<ProductTypeWithIdDto> createProductType(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                                  @Valid @RequestBody ProductTypeDto productType) {
        // todo jwt validation
        log.info("createProductType: ENTRY");
        ProductTypeWithIdDto productTypeWithId = productTypeMapper.toModel(
                productTypeService.createProductType(productTypeMapper.toModel(productType)));
        log.info("createProductType: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(productTypeWithId);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletes Type",
            description = "Deletes Type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete Type",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> deleteProductType(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                        @Valid @RequestBody ProductTypeIdDto productTypeId) {
        // todo jwt validation
        log.info("deleteProductType: ENTRY");
        Long id = Long.parseLong(productTypeId.getIdProductType());
        productTypeService.removeProductType(id);
        log.info("deleteProductType: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("deleteProductType completed"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates Type",
            description = "Updates Type")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update successful"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<?> updateProductType(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                               @Valid @RequestBody ProductTypeWithIdDto productTypeWithId) {
        // todo jwt validation
        log.info("updateProductType: ENTRY");
        productTypeService.updateProductType(productTypeMapper.toModel(productTypeWithId));
        log.info("updateProductType: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("updateProductType completed"));
    }

}
