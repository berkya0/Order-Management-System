package com.berkaykomur.ordermanagement.external;

import com.berkaykomur.ordermanagement.exception.ExternalServiceException;
import com.berkaykomur.ordermanagement.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TurkiyeApiService {

    private final WebClient webClient;
    private static final String PATH = "/api/autocomplete";

    public TurkiyeApiAddressResponse search(String query) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(PATH)
                            .queryParam("q", query)
                            .queryParam("limit", 5)
                            .build())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response ->
                                    Mono.error(new ExternalServiceException("Turkiye api client error for query" + query,
                                            HttpStatus.BAD_REQUEST)))
                    .onStatus(status -> status.is5xxServerError(),
                            response ->
                                    Mono.error(new ExternalServiceException("Turkiye api server error for query" + query,
                                            HttpStatus.BAD_GATEWAY)))
                    .bodyToMono(TurkiyeApiAddressResponse.class)
                    .blockOptional()
                    .orElseThrow(() -> new ResourceNotFoundException("Turkiye api returned empty reponse for query"+query));
        } catch (WebClientRequestException e) {
            throw new ExternalServiceException(
                    "Turkiye api request failed: " + e.getMessage(),
                    HttpStatus.GATEWAY_TIMEOUT);
        }

    }
}


