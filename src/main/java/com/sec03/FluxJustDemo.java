package com.sec03;

import reactor.core.publisher.Flux;

import java.util.List;

public class FluxJustDemo {
    public static void main(String[] args) {
        Flux.just(1, 2, 3).subscribe((item) -> System.out.println(item));
        var flux1 = Flux.just(1, 2, 3, 4, 5, 6);
        flux1.subscribe(item -> System.out.println("sub 1: " + item),
                err -> {},
                () -> System.out.println("Subscriber 1 completed"));
        flux1.subscribe(item -> System.out.println("sub 2: " + item),
                err -> {},
                () -> System.out.println("Subscriber 2 completed"));
        flux1.filter(i -> i % 2 == 0).map(i -> i * i).subscribe(i -> System.out.println(i));

        var list = List.of(1, 2, 3, 4);
        Flux.fromIterable(list).subscribe(i -> System.out.println(i));

        // stream can be consumed only once
        var simpleList = List.of(1, 2, 3, 4, 5);
        var simpleStream = simpleList.stream();
        var flux = Flux.fromStream(simpleStream);
        flux.subscribe(i -> System.out.println("Sub 1 received from stream: " + i),
                err -> System.out.println("Sub 1 received error: " + err.getMessage()));
        flux.subscribe(i -> System.out.println("Sub 2 received from stream: " + i),
                err -> System.out.println("Sub 2 received error: " + err.getMessage()));

        // stream can be consumed multiple times as we re-create the stream every time
        var nexList = List.of(2, 3, 5);
        var nexFlux = Flux.fromStream(nexList::stream);
        nexFlux.subscribe(i -> System.out.println("Nex Sub 1 received from stream: " + i),
                err -> System.out.println("Nex Sub 1 received error: " + err.getMessage()));
        nexFlux.subscribe(i -> System.out.println("Nex Sub 2 received from stream: " + i),
                err -> System.out.println("Nex Sub 2 received error: " + err.getMessage()));
    }
}
