package com.sec02;

import com.common.Util;
import com.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonBlockingClientDemo {
    private final static Logger logger = LoggerFactory.getLogger(NonBlockingClientDemo.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        logger.info("Starting...");
        for(int i = 1; i <= 5; i++) {
            client.getProductName(i).subscribe(Util.subscriber());
        }
        Util.sleep(2);
    }
}
