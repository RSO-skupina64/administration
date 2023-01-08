package com.rso.microservice.api;

import com.rso.microservice.api.dto.ErrorDto;
import com.rso.microservice.api.dto.MessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Tag(name = "Health")
public class HealthAPI {
    private static final Logger log = LoggerFactory.getLogger(HealthAPI.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ApplicationAvailability applicationAvailability;

    @PostMapping(value = "/liveness", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Changes liveness",
            description = "Changes liveness of application (ONLY FOR DEMONSTRATION PURPOSES)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Change successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> changeLiveness() {
        log.info("changeLiveness: ENTRY");
        log.info(".changeLiveness changing liveness state");
        if (applicationAvailability.getLivenessState().equals(LivenessState.CORRECT)) {
            log.info(".changeLiveness changing to BROKEN");
            AvailabilityChangeEvent.publish(applicationContext, LivenessState.BROKEN);
        } else {
            log.info(".changeLiveness changing to CORRECT");
            AvailabilityChangeEvent.publish(applicationContext, LivenessState.CORRECT);
        }
        MessageDto response = new MessageDto("Change successful");
        log.info("changeLiveness: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/readiness", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Changes readiness",
            description = "Changes readiness of application (ONLY FOR DEMONSTRATION PURPOSES)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Change successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    public ResponseEntity<MessageDto> changeReadiness() {
        log.info("changeReadiness: ENTRY");
        log.info(".changeReadiness changing readiness state");
        if (applicationAvailability.getReadinessState().equals(ReadinessState.ACCEPTING_TRAFFIC)) {
            log.info(".changeReadiness changing to REFUSING_TRAFFIC");
            AvailabilityChangeEvent.publish(applicationContext, ReadinessState.REFUSING_TRAFFIC);
        } else {
            log.info(".changeReadiness changing to ACCEPTING_TRAFFIC");
            AvailabilityChangeEvent.publish(applicationContext, ReadinessState.ACCEPTING_TRAFFIC);
        }
        MessageDto response = new MessageDto("Change successful");
        log.info("changeReadiness: EXIT");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
