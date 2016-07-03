package com.demo.service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by qinwen on 16/7/3.
 */
public interface UserService extends Remote, Serializable{

    User getUser(Long id, String name) throws RemoteException;

}
