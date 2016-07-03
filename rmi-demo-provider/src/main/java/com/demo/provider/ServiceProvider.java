package com.demo.provider;

import com.demo.service.Constant;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.CountDownLatch;

import static com.demo.service.Constant.ZK_PROVIDER_PATH;

/**
 * RMI服务提供者
 */
public class ServiceProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);

    // 用于等待 SyncConnected 事件触发后继续执行当前线程
    private CountDownLatch latch = new CountDownLatch(1);

    private String host;

    private Integer port;

    private Registry registry;

    public ServiceProvider() {
    }

    public ServiceProvider(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    // 发布 RMI 服务并注册 RMI 地址到 ZooKeeper 中
    public void publish(Remote remote, String className, String host, int port) {
        String url = publishService(remote, host, port); // 发布 RMI 服务并返回 RMI 地址
        if (url != null) {
            ZooKeeper zk = connectServer(); // 连接 ZooKeeper 服务器并获取 ZooKeeper 对象
            if (zk != null) {
                createNode(zk, className, url); // 创建 ZNode 并将 RMI 地址放入 ZNode 上
            }
        }
    }

    public void publish(Remote remote, String className) {
        this.publish(remote, className, host, port);
    }

    // 发布 RMI 服务
    private String publishService(Remote remote, String host, int port) {
        String url = null;
        try {
            if (registry == null) {
                registry = LocateRegistry.createRegistry(port);
            }

            url = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());

            Naming.rebind(url, remote);
            LOGGER.debug("publish rmi service (url: {})", url);
        } catch (RemoteException | MalformedURLException e) {
            LOGGER.error("", e);
        }
        return url;
    }

    // 连接 ZooKeeper 服务器
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown(); // 唤醒当前正在执行的线程
                    }
                }
            });
            latch.await(); // 使当前线程处于等待状态
        } catch (IOException | InterruptedException e) {
            LOGGER.error("", e);
        }
        return zk;
    }

    // 创建 ZNode
    private void createNode(ZooKeeper zk, String className, String url) {
        try {
            String regPath = Constant.ZK_REGISTRY_PATH + "/" + className;

            if (zk.exists(regPath, false) == null) {
                zk.create(regPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            byte[] data = url.getBytes();
            String path = zk.create(regPath + ZK_PROVIDER_PATH, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL); // 创建一个临时性且有序的 ZNode
            LOGGER.debug("create zookeeper node ({} => {})", path, url);
        } catch (KeeperException | InterruptedException e) {
            LOGGER.error("", e);
        }
    }
}