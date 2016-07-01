package com.demo.service.provider;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author qinwen
 * @create 2016-07-01 10:37
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    private static final long serialVersionUID = -2774453589330690036L;

    protected HelloServiceImpl() throws RemoteException {

    }

    public String sayHello(String name) throws RemoteException {
        return String.format("Hello %s", name);
    }

}
