package com.yonyou.soa.invoke;

import com.yonyou.soa.LoadBalance.LoadBalance;
import com.yonyou.soa.LoadBalance.NodeInfo;
import com.yonyou.soa.configbean.Reference;

import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/21 15:52
 */

public class HttpInvoker implements Invoke {
    public String invoke(Invocation invocation) {
        Reference reference = invocation.getRef();
        List<String> registryInfo = reference.getRegistryInfo();
        String loadbalance = reference.getLoadbalance();
        LoadBalance loadBalance = Reference.getLoadBalanceMap().get(loadbalance);
        System.out.println("------------负载均衡拉--------------");
        NodeInfo nodeInfo = loadBalance.doSelect(registryInfo);
        return nodeInfo.toString();
    }
}
