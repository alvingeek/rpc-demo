import com.caucho.hessian.client.HessianProxyFactory;
import com.demo.hessian.api.BasicAPI;

import java.net.MalformedURLException;

/**
 * @author qinwen
 * @create 2016-07-06 16:18
 */
public class BasicServiceTest {
    public static void main(String[] args) throws MalformedURLException {
        String url = "";
        HessianProxyFactory factory = new HessianProxyFactory();
        BasicAPI basic = (BasicAPI) factory.create(BasicAPI.class, url);

        System.out.println("hello(): " + basic.hello());
    }
}
