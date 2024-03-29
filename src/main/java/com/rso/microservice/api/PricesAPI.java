package com.rso.microservice.api;

import com.rso.microservice.api.dto.ErrorDto;
import com.rso.microservice.api.dto.MessageDto;
import com.rso.microservice.service.PricesService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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

@RestController
@RequestMapping("/prices")
@OpenAPIDefinition(info = @Info(title = "Administration API",
        description = "This is API documentation for Administration Microservice",
        version = "0.1"))
@Tag(name = "Prices")
public class PricesAPI {
    private static final Logger log = LoggerFactory.getLogger(PricesAPI.class);

    private final PricesService pricesService;

    public PricesAPI(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Fetches prices for all shops",
            description = "Fetches prices for all shops")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prices",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> fetchProductPrices(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                         @RequestParam(required = false, defaultValue = "false") boolean fetchPictures) {
        log.info("fetchProductPrices: ENTRY");
        String response = pricesService.fetchProductPrices(jwt, fetchPictures);
        log.info("fetchProductPrices: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto(response));
    }

    @GetMapping(value = "/shop/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Fetches prices for specific shop",
            description = "Fetches prices for specific shop")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prices",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> fetchProductPricesSpecificShop(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @PathVariable String id,
            @RequestParam(required = false, defaultValue = "false") boolean fetchPictures) {
        log.info("fetchProductPricesSpecificShop: ENTRY");
        String response = pricesService.fetchProductPricesSpecificShop(jwt, id, fetchPictures);
        log.info("fetchProductPricesSpecificShop: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto(response));
    }

}
