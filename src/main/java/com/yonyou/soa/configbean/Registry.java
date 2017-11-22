package com.yonyou.soa.configbean;

import com.yonyou.soa.registry.BaseRegistry;
import com.yonyou.soa.registry.RedisRegistry;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/18 16:01
 */

public class Registry implements Serializable{
    private static final long serialVersionUID = 213445673642257L;
    private String protocol;
    private String address;
    private static HashMap<String,BaseRegistry> registryMap=new HashMap<String, BaseRegistry>();
    static{
        registryMap.put("zookeeper",null);
        registryMap.put("redis", new  RedisRegistry());
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static HashMap<String, BaseRegistry> getRegistryMap() {
        return registryMap;
    }
}
