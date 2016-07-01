package com.demo.service.consumer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author qinwen
 * @create 2016-07-01 17:48
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
    }
}
