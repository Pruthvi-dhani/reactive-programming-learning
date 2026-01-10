package com.sec02;

import com.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromSupplier {
    private static final Logger logger = LoggerFactory.getLogger(MonoFromSupplier.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3);
        Mono.fromSupplier(() -> {
            try {
                return sum(list);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).subscribe(i -> logger.info("Received: {}", i));

        Mono.fromCallable(() -> sum(list)).subscribe(Util.subscriber());


    }

    private static int sum(List<Integer> list) throws Exception {
        logger.info("Finding the sum of {}", list);
        return list.stream().mapToInt(i -> i).sum();
    }
}
