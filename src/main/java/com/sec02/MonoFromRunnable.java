package com.sec02;

import com.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromRunnable {
    private static Logger logger = LoggerFactory.getLogger(MonoFromSupplier.class);

    public static void main(String[] args) {
        getProductName(1).subscribe(Util.subscriber());
        getProductName(2).subscribe(Util.subscriber());

        Mono.fromFuture(() -> getName()).subscribe(Util.subscriber());
        Util.sleep(1);
    }

    private static Mono<String> getProductName(int productId) {
        if(productId == 1) return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId) {
        logger.info("Notifying business about unavailability of productId: {}", productId);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(
                () -> {
                    logger.info("Generating name...");
                    return Util.faker().name().firstName();
                }
        );
    }
}
