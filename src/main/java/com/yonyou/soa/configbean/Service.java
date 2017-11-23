package com.yonyou.soa.configbean;

import com.yonyou.soa.registry.BaseRegistryDelegate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * 一个service实例代表一个配置文件中的标签，配置多个就会有多个service，会向注册中心注册
 * <ruochen:service interface="com.yonyou.soa.test.TestService" ref="testService1" protocol="netty"></ruochen:service>
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:14
 */

public class Service implements Serializable,InitializingBean,ApplicationContextAware{
    private static final long serialVersionUID = 123457687682257L;
    private String inter;
    private String ref;
    private String protocol;
    private static ApplicationContext applicationContext;
    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void afterPropertiesSet() throws Exception {
        BaseRegistryDelegate.regist(ref,applicationContext);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
