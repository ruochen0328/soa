package com.yonyou.soa.configbean;

import java.io.Serializable;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:06
 */

public class Protocol implements Serializable{
    private static final long serialVersionUID = 1232345322257L;
    private String name;
    private String port;
    private String host;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
