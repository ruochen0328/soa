package com.yonyou.soa.invoke;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/21 15:52
 */

public class HttpInvoker implements Invoke {
    public String invoke(Invocation invocation) {
        System.out.println("代理类全部走到了这里");
        return null;
    }
}
