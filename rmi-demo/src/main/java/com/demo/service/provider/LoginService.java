package com.demo.service.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginService extends Remote{

    String login(String username, String password) throws RemoteException;

}
