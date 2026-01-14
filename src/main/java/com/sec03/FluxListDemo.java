package com.sec03;

import com.sec03.helper.NameGenerator;

public class FluxListDemo {
    public static void main(String[] args) {
        // takes a long time
        var list = NameGenerator.generateNamesList(10);
        System.out.println("List Names: " + list);

        var flux = NameGenerator.generateNamesFlux(10);
        flux.subscribe(item -> System.out.println("Flux Name: " + item));
    }
}
