package com.demo.provider.hello;

import com.demo.service.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author qinwen
 * @create 2016-07-01 10:37
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    private static final long serialVersionUID = -2774453589330690036L;

    public HelloServiceImpl() throws RemoteException {

    }

    public String sayHello(String name) throws RemoteException {
        return String.format("Hello %s", name);
    }

}
