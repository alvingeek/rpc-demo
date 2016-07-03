package com.demo.provider.user;

import com.demo.service.User;
import com.demo.service.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by qinwen on 16/7/3.
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private static final long serialVersionUID = 6904824977285077461L;

    public UserServiceImpl() throws RemoteException {
    }

    @Override
    public User getUser(Long id, String name) throws RemoteException {
        return new User(id, name);
    }

}
