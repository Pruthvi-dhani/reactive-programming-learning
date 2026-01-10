//package com.sec02;
//
//import com.common.DefaultSubscriber;
//import org.reactivestreams.Publisher;
//import reactor.core.publisher.Mono;
//
//public class MonoJustExample {
//    public static void main(String[] args) {
//        var mono = Mono.just("abc");
//        System.out.println(mono);
//
//        var subscriber = new DefaultSubscriber();
//        mono.subscribe(subscriber);
//
//        subscriber.getSubscription().request(10);
//        subscriber.getSubscription().request(10);
//        subscriber.getSubscription().cancel();
//
//        save(Mono.just("123"));
//    }
//
//    private static void save(Publisher<String> publisher) {
//
//    }
//}
