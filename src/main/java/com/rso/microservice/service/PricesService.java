package com.rso.microservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rso.microservice.api.dto.MessageDto;
import com.rso.microservice.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final PricesService pricesService;

    @Value("${microservice.prices.url}")
    private String pricesUrl;

    public PricesService(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    public String fetchPrices(String jwt) {
        log.info(".fetchPrices fetching prices from URL: {}", pricesUrl);
        String requestId = MDCUtil.get(MDCUtil.MDCUtilKey.REQUEST_ID);
        MessageDto response = pricesService.callPricesUrl(jwt, requestId);
        log.info("received response: {}", response.getMessage());
        return response.getMessage();
    }

    @HystrixCommand(fallbackMethod = "circuitBreaker")
    public MessageDto callPricesUrl(String jwt, String requestId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, jwt);
        headers.add("X-Request-Id", requestId);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<MessageDto> response = new RestTemplate().exchange(pricesUrl, HttpMethod.GET, requestEntity, MessageDto.class);
        return response.getBody();
    }

    public MessageDto circuitBreaker(String jwt, String requestId) {
        log.error("There was an error when calling fetchPrices, so circuit breaker was activated");
        return new MessageDto("Error while calling prices, circuit breaker method called");
    }

}
