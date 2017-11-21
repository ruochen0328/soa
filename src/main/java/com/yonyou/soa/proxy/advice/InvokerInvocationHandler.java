package com.yonyou.soa.proxy.advice;

import com.yonyou.soa.configbean.Reference;
import com.yonyou.soa.invoke.Invocation;
import com.yonyou.soa.invoke.Invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/21 15:46
 */

public class InvokerInvocationHandler implements InvocationHandler {
    private Invoke invoke;
    private Reference reference;

    public InvokerInvocationHandler(Invoke invoke, Reference reference) {
        this.invoke = invoke;
        this.reference = reference;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation=new Invocation();
        invocation.setMethod(method);
        invocation.setObj(args);
        invocation.setRef(reference);
        this.invoke.invoke(invocation);
        return null;
    }
}
