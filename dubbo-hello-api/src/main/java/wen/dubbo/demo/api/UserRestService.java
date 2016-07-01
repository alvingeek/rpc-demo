package wen.dubbo.demo.api;

import javax.validation.constraints.Min;

/**
 * @author qinwen
 * @create 2016-06-28 11:25
 */
public interface  UserRestService {
    User getUser(@Min(value = 1L, message = "User ID must be greater than 1") Long id);
}
