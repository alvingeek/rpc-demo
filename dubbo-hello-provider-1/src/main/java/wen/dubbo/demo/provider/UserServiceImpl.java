package wen.dubbo.demo.provider;

import com.alibaba.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import wen.dubbo.demo.api.User;
import wen.dubbo.demo.api.UserService;

/**
 * @author qinwen
 * @create 2016-06-28 11:30
 */
public class UserServiceImpl implements UserService {
    public User getUser(Long id) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + id + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return new User(id, "from c2: username" + id);
    }
}
