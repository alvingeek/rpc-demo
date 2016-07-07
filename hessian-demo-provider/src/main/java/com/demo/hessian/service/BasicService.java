package com.demo.hessian.service;

import com.caucho.hessian.server.HessianServlet;
import com.demo.hessian.api.BasicAPI;

/**
 * @author qinwen
 * @create 2016-07-06 16:03
 */
public class BasicService extends HessianServlet implements BasicAPI {
    private String greeting = "Hello, world";

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String hello() {
        return greeting;
    }

}
