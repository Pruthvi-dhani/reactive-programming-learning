package com.sec03;

import com.sec03.client.ExternalServiceClient;
import com.sec03.helper.Section3AssignmentSubscriber;

import java.time.Duration;

public class Section3Assignment {
    public static void main(String[] args) throws InterruptedException {
        var apiClient = new ExternalServiceClient();
        apiClient.getStockPrices().subscribe(new Section3AssignmentSubscriber());
        Thread.sleep(Duration.ofSeconds(30));
    }
}
