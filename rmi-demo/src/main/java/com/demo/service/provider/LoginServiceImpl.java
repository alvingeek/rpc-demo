package com.demo.service.provider;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author qinwen
 * @create 2016-07-01 11:01
 */
public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {

    private static final long serialVersionUID = 6242776730913443904L;

    protected LoginServiceImpl() throws RemoteException {

    }

    public String login(String username, String password) {
        return String.format("Login Succeed, %s %s", username, password);
    }

}
