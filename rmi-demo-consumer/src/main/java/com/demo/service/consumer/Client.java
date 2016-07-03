package com.demo.service.consumer;


import com.demo.service.HelloService;
import com.demo.service.User;
import com.demo.service.UserService;

/**
 * RMI客户端
 */
public class Client {

    public static void main(String[] args) throws Exception {
        ServiceConsumer consumer = new ServiceConsumer();

        while (true) {
            HelloService helloService = consumer.lookup(HelloService.class.getName());
            String result = helloService.sayHello("Jack");
            System.out.println(result);
            UserService userService = consumer.lookup(UserService.class.getName());
            User user = userService.getUser(1L, "Tome");
            System.out.println(user);
            Thread.sleep(3000);
        }
    }
}
