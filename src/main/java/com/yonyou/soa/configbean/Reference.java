package com.yonyou.soa.configbean;

import java.io.Serializable;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 18:48
 */

public class Reference implements Serializable{
    private static final long serialVersionUID = 123456736426587L;
    private String id;
    private String ref;
    private String loadbalance;
    private String protocol;

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
}
