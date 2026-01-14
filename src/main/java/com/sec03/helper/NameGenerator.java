package com.sec03.helper;

import com.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static List<String> generateNamesList(int count) {
        // ordinary imperative programming
        return IntStream.rangeClosed(1, count).mapToObj(i -> generateName()).toList();
    }

    public static Flux<String> generateNamesFlux(int count) {
        // ordinary imperative programming
        return Flux.range(1, count).map(i -> generateName());
    }

    private static String generateName() {
        // simulate time taking operation here
        Util.sleep(1);
        return Util.faker().name().firstName();
    }
}
