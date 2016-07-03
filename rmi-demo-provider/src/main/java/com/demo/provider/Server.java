package com.demo.provider;

import com.demo.provider.user.UserServiceImpl;
import com.demo.service.HelloService;
import com.demo.provider.hello.HelloServiceImpl;
import com.demo.service.UserService;

/**
 *  *  服务发布
 *  
 */
public class Server {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("please  using  command:java Server <rmi_host> <rmi_port>");
            System.exit(-1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        ServiceProvider provider = new ServiceProvider(host, port);

        HelloService helloService = new HelloServiceImpl();
        provider.publish(helloService, HelloService.class.getName(), host, port);
        UserService userService = new UserServiceImpl();
        provider.publish(userService, UserService.class.getName());

        Thread.sleep(Long.MAX_VALUE);
    }
}