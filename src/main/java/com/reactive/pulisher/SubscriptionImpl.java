package com.reactive.pulisher;

import com.github.javafaker.Faker;
import com.common.DefaultSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSubscriber.class);

    private boolean isCancelled = false;

    private Subscriber<? super String> subscriber;

    private Faker faker;

    private static final int MAX_ITEMS = 10;

    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if(isCancelled) {
            return;
        }
        logger.info("subscriber has requested {} items", requested);
        for(int i = 0; i < requested && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext(faker.internet().emailAddress());
        }
        if(count == MAX_ITEMS) {
            logger.info("Max items have been produced");
            subscriber.onComplete();
            isCancelled = true;
        }

    }

    @Override
    public void cancel() {
        logger.info("subscription has cancelled");
        isCancelled = true;
    }
}
