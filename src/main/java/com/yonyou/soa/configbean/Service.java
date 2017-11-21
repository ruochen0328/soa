package com.yonyou.soa.configbean;

import java.io.Serializable;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:14
 */

public class Service implements Serializable{
    private static final long serialVersionUID = 123457687682257L;
    private String inter;
    private String ref;
    private String protocol;

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
}
