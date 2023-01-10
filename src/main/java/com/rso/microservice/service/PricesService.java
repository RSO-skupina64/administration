package com.rso.microservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rso.microservice.api.dto.MessageDto;
import com.rso.microservice.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    public String fetchPrices(String jwt, boolean fetchPictures) {
        log.info("fetchPrices fetching prices from URL: {}", pricesUrl);
        String requestId = MDCUtil.get(MDCUtil.MDCUtilKey.REQUEST_ID);
        String version = MDCUtil.get(MDCUtil.MDCUtilKey.MICROSERVICE_VERSION);
        MessageDto response = pricesService.callPricesUrl(jwt, fetchPictures, requestId, version);
        log.info("received response: {}", response.getMessage());
        return response.getMessage();
    }


    public String fetchPricesSpecificShop(String jwt, String id, boolean fetchPictures) {
        String pricesUrlSpecificShop = String.format("%s/shop", pricesUrl);
        log.info("fetchPrices fetching prices from URL: {}", pricesUrlSpecificShop);
        String requestId = MDCUtil.get(MDCUtil.MDCUtilKey.REQUEST_ID);
        String version = MDCUtil.get(MDCUtil.MDCUtilKey.MICROSERVICE_VERSION);
        MessageDto response = pricesService.callPricesSpecificShopUrl(jwt, id, fetchPictures, requestId, version);
        log.info("received response: {}", response.getMessage());
        return response.getMessage();
    }

    @HystrixCommand(fallbackMethod = "circuitBreaker")
    public MessageDto callPricesUrl(String jwt, boolean fetchPictures, String requestId, String version) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(pricesUrl)
                .queryParam("fetchPictures", fetchPictures);
        String url = builder.toUriString();

        MDCUtil.putAll("Administration", version, requestId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, jwt);
        headers.add("X-Request-Id", requestId);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<MessageDto> response = new RestTemplate().exchange(url, HttpMethod.GET, requestEntity,
                MessageDto.class);
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "circuitBreakerSpecificShop")
    public MessageDto callPricesSpecificShopUrl(String jwt, String id, boolean fetchPictures, String requestId,
                                                String version) {
        String url = String.format("%s/shop/%s", pricesUrl, id);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("fetchPictures", fetchPictures);
        url = builder.toUriString();

        MDCUtil.putAll("Administration", version, requestId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, jwt);
        headers.add("X-Request-Id", requestId);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<MessageDto> response = new RestTemplate().exchange(url, HttpMethod.GET, requestEntity,
                MessageDto.class);
        return response.getBody();
    }

    public MessageDto circuitBreaker(String jwt, boolean fetchPictures, String requestId, String version) {
        MDCUtil.putAll("Administration", version, requestId);
        log.error("There was an error when calling fetchPrices, so circuit breaker was activated");
        return new MessageDto("Error while calling prices, circuit breaker method called");
    }

    public MessageDto circuitBreakerSpecificShop(String jwt, String id, boolean fetchPictures, String requestId,
                                                 String version) {
        MDCUtil.putAll("Administration", version, requestId);
        log.error("There was an error when calling fetchPricesSpecificShop, so circuit breaker was activated");
        return new MessageDto("Error while calling prices, circuit breaker method called");
    }

}
