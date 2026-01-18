package com.sec03.helper;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class Section3AssignmentSubscriber extends BaseSubscriber<Integer> {
    private final int startBalance = 1000;

    private int currBalance = startBalance;

    private int currQuantity = 0;

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed...");
        request(1);
    }

    @Override
    protected void hookOnNext(Integer value) {
        System.out.println("Current stock price is: " + value);
        if(value < 90 && currBalance >= value) {
            currQuantity++;
            currBalance -= value;
        } else if(value > 110) {
            System.out.println("Profit made is: " + (currBalance - 1000 + currQuantity * value));
            cancel();
        }
        request(1);
    }

    @Override
    protected void hookOnCancel() {
        System.out.println("Cancelling subscription...");
    }

    @Override
    protected void hookOnError(Throwable err) {
        System.out.println("Received error: " + err.getMessage());
    }
}
