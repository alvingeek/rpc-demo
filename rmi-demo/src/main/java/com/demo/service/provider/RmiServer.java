package com.demo.service.provider;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author qinwen
 * @create 2016-07-01 10:48
 */
public class RmiServer {
    public static void main(String[] args) throws Exception {
        int port = 1099;
        String url = "com.demo.service.HelloServiceImpl";
        String urlLoginService = "com.demo.service.provider.LoginServiceImpl";
        LocateRegistry.createRegistry(port);
        Naming.rebind(url, new HelloServiceImpl());
        Naming.rebind(urlLoginService, new LoginServiceImpl());

        String localIp = null;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println("本机的ip是 ：" + localIp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(String.format("server is running on %s:%s", localIp, port));
    }
}
