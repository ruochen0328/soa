package com.yonyou.soa.configbean;

import java.io.Serializable;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/18 16:01
 */

public class Registry implements Serializable{
    private static final long serialVersionUID = 213445673642257L;
    private String protocol;
    private String address;

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
}
