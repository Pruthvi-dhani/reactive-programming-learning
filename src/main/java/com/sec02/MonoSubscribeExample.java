package com.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoSubscribeExample {
    private static Logger logger = LoggerFactory.getLogger(MonoSubscribeExample.class);

    public static void main(String[] args) {
        var mono = Mono.just(1).map(i -> i + "a");

        mono.subscribe(i -> logger.info("Value received: {}", i),
                err -> logger.info("Received error: {}", err.getMessage()),
                () -> logger.info("Completed"),
                subscription -> subscription.request(1));
    }
}
