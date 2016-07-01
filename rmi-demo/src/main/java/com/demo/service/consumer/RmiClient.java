package com.demo.service.consumer;

import com.demo.service.provider.HelloService;
import com.demo.service.provider.LoginService;

import java.rmi.Naming;
import java.rmi.Remote;

/**
 * @author qinwen
 * @create 2016-07-01 10:53
 */
public class RmiClient {
    public static void main(String[] args) throws Exception {
        String ip = "192.168.195.128";
        //String ip = "localhost";
        String url = "rmi://" + ip + ":1099/com.demo.service.HelloServiceImpl";
        String urlLoginService = "rmi://" + ip + ":1099/com.demo.service.provider.LoginServiceImpl";
        HelloService helloService = (HelloService) Naming.lookup(url);

        LoginService loginService = null;

        Remote lookup = Naming.lookup(urlLoginService);

        if (lookup instanceof LoginService) {
            System.out.println("lookup is a LoginService instance");
            loginService = (LoginService) lookup;
        }

        String result = helloService.sayHello("Tom");
        System.out.println(result);

        String loginResult = loginService.login("Jack", "s");
        System.out.println(loginResult);
    }
}