package com.rso.microservice.api;

import com.rso.microservice.api.dto.*;
import com.rso.microservice.api.mapper.ProductMapper;
import com.rso.microservice.service.ProductService;
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
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductAPI {
    private static final Logger log = LoggerFactory.getLogger(ProductAPI.class);

    final ProductService productService;

    final ProductMapper productMapper;

    public ProductAPI(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates new product",
            description = "Creates new product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Create new product",
                    content = @Content(schema = @Schema(implementation = ProductWithIdDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<ProductWithIdDto> createProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                          @Valid @RequestBody ProductDto product) {
        // todo jwt validation
        log.info("createProduct: ENTRY");
        ProductWithIdDto productWithId = productMapper.toModel(productService.createProduct(productMapper.toModel(product), product.getIdProductType()));
        log.info("createProduct: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(productWithId);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletes product",
            description = "Deletes product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete product",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> deleteProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                    @Valid @RequestBody ProductIdDto productId) {
        // todo jwt validation
        log.info("deleteProduct: ENTRY");
        productService.removeProduct(productId.getIdProduct());
        log.info("deleteProduct: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("deleteProduct completed"));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates product",
            description = "Updates product")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update successful"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<?> updateProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                           @Valid @RequestBody ProductWithIdDto productWithId) {
        // todo jwt validation
        log.info("updateProduct: ENTRY");
        productService.updateProduct(productMapper.toModel(productWithId), productWithId.getIdProductType());
        log.info("updateProduct: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("updateProduct completed"));
    }

}
