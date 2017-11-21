package com.yonyou.soa.invoke;

import com.yonyou.soa.configbean.Reference;

import java.lang.reflect.Method;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/21 15:38
 */

public class Invocation {
    private Method method;
    private Object[] obj;
    private Reference ref;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getObj() {
        return obj;
    }

    public void setObj(Object[] obj) {
        this.obj = obj;
    }

    public Reference getRef() {
        return ref;
    }

    public void setRef(Reference ref) {
        this.ref = ref;
    }
}
