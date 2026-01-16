package com.sec03.client;

import com.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getNames() {
        return httpClient.get().uri("/demo02/name/stream").responseContent().asString();
    }
}
