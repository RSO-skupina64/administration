package com.rso.microservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rso.microservice.api.dto.MessageDto;
import com.rso.microservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class PricesService {
    private static final Logger log = LoggerFactory.getLogger(PricesService.class);

    @Value("${microservice.prices.url}")
    private String pricesUrl;

    @HystrixCommand(fallbackMethod = "circuitBreaker")
    public String fetchPrices(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, jwt);
        headers.add("X-Request-Id", "");

        log.info(".fetchPrices fetching prices from URL: {}", pricesUrl);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<MessageDto> response = new RestTemplate().exchange(pricesUrl, HttpMethod.GET, requestEntity, MessageDto.class);
        log.info("received response: {}", response.getBody().getMessage());
        return response.getBody().getMessage();
    }

    public String circuitBreaker(String jwt) {
        log.error("There was an error when calling fetchPrices, so circuit breaker was activated");
        return "Error while calling prices";
    }

}
