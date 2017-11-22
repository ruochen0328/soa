package com.yonyou.soa.configbean;

import com.yonyou.soa.LoadBalance.LoadBalance;
import com.yonyou.soa.LoadBalance.RandomLoadBalance;
import com.yonyou.soa.LoadBalance.RroundrobBalance;
import com.yonyou.soa.invoke.HttpInvoker;
import com.yonyou.soa.invoke.Invoke;
import com.yonyou.soa.proxy.advice.InvokerInvocationHandler;
import com.yonyou.soa.registry.BaseRegistryDelegate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 18:48
 */

public class Reference implements Serializable,FactoryBean ,InitializingBean,ApplicationContextAware{
    private static final long serialVersionUID = 123456736426587L;
    private static Map<String,Invoke> invokes=new HashMap<String, Invoke>();
    private List<String> registryInfo=new ArrayList<String>();
    private static Map<String,LoadBalance> loadBalanceMap=new HashMap<String, LoadBalance>();
    private ApplicationContext applicationContext;
    private String id;
    private String ref;
    private String loadbalance;
    private String protocol;
    private Invoke invoke;
    static {
        invokes.put("http",new HttpInvoker());
        invokes.put("rmi",null);

        loadBalanceMap.put("random",new RandomLoadBalance());
        loadBalanceMap.put("roundrob",new RroundrobBalance());
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

    public List<String> getRegistryInfo() {
        return registryInfo;
    }

    public void setRegistryInfo(List<String> registryInfo) {
        this.registryInfo = registryInfo;
    }

    public static Map<String, LoadBalance> getLoadBalanceMap() {
        return loadBalanceMap;
    }

    public static void setLoadBalanceMap(Map<String, LoadBalance> loadBalanceMap) {
        Reference.loadBalanceMap = loadBalanceMap;
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

    public void afterPropertiesSet() throws Exception {
        List<String> registrys = BaseRegistryDelegate.getRegistry(id, applicationContext);
        this.registryInfo=registrys;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
