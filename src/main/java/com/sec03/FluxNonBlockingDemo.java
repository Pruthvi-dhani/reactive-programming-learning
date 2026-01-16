package com.sec03;

import com.common.Util;
import com.sec03.client.ExternalServiceClient;

public class FluxNonBlockingDemo {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        client.getNames().subscribe(i -> System.out.println("subs 1 http resp: " + i));
        client.getNames().subscribe(i -> System.out.println("subs 2 http resp: " + i));
        Util.sleep(6);
    }
}
