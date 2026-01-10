//package com.reactive;
//
//import com.reactive.pulisher.PublisherImpl;
//import com.common.DefaultSubscriber;
//
//import java.time.Duration;
//
//public class Demo {
//    public static void main(String[] args) throws InterruptedException {
//        demo1();
//        demo2();
//    }
//
//    private static void demo1() {
//        var publisher = new PublisherImpl();
//        var subscriber = new DefaultSubscriber();
//        publisher.subscribe(subscriber);
//    }
//
//    private static void demo2() throws InterruptedException {
//        var publisher = new PublisherImpl();
//        var subscriber = new DefaultSubscriber();
//        publisher.subscribe(subscriber);
//        subscriber.getSubscription().request(3);
//        Thread.sleep(Duration.ofSeconds(2));
//        subscriber.getSubscription().request(3);
//        Thread.sleep(Duration.ofSeconds(2));
//        subscriber.getSubscription().request(3);
//        Thread.sleep(Duration.ofSeconds(2));
//        subscriber.getSubscription().request(3);
//        Thread.sleep(Duration.ofSeconds(2));
//    }
//}
