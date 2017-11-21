package com.yonyou.soa.configbean;

import com.yonyou.soa.invoke.HttpInvoker;
import com.yonyou.soa.invoke.Invoke;
import com.yonyou.soa.proxy.advice.InvokerInvocationHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 18:48
 */

public class Reference implements Serializable,FactoryBean {
    private static final long serialVersionUID = 123456736426587L;
    private static Map<String,Invoke> invokes=new HashMap<String, Invoke>();
    private String id;
    private String ref;
    private String loadbalance;
    private String protocol;
    private Invoke invoke;
    static {
        invokes.put("http",new HttpInvoker());
        invokes.put("rmi",null);
    }

    public Reference(){
        System.out.println("------------Reference实例化------------");
    }
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Invoke getInvoke() {
        return invoke;
    }

    public void setInvoke(Invoke invoke) {
        this.invoke = invoke;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Object getObject() throws Exception {
        if (!StringUtils.isEmpty(protocol)){
            invoke= invokes.get(protocol);
            if (invoke==null){
                throw new RuntimeException("ref tag miss");
            }
        }else{
            invoke=invokes.get("http");
        }
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Class.forName(ref)},new InvokerInvocationHandler(invoke,this));
    }

    public Class<?> getObjectType() {
        if (!StringUtils.isEmpty(ref)){
            try {
                return Class.forName(this.ref);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean isSingleton() {
        return true;
    }
}
