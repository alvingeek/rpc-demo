package wen.dubbo.demo.provider;

import wen.dubbo.demo.api.CalcService;

/**
 * @author qinwen
 * @create 2016-06-28 18:13
 */
public class CalcServiceImpl implements CalcService {
    public int add(int x, int y) {
        return x + y;
    }
}
