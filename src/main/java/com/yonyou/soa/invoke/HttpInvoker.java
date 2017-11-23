package com.yonyou.soa.invoke;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.soa.LoadBalance.LoadBalance;
import com.yonyou.soa.LoadBalance.NodeInfo;
import com.yonyou.soa.configbean.Reference;
import com.yonyou.soa.rpc.http.HttpRequest;

import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/21 15:52
 */

public class HttpInvoker implements Invoke {
    public String invoke(Invocation invocation) throws Exception {
        try {
            Reference reference = invocation.getRef();
            List<String> registryInfo = reference.getRegistryInfo();
            String loadbalance = reference.getLoadbalance();
            LoadBalance loadBalance = Reference.getLoadBalanceMap().get(loadbalance);
            //NodeInfo封装到哪个节点去调用
            NodeInfo nodeinfo = loadBalance.doSelect(registryInfo);
            JSONObject toInvoke=new JSONObject();
            toInvoke.put("methodName",invocation.getMethod().getName());
            toInvoke.put("methodParam",invocation.getObj());
            toInvoke.put("serviceId",reference.getId());
            toInvoke.put("paramType",invocation.getMethod().getParameterTypes());

            String url = "http://" + nodeinfo.getHost() + ":"
                    + nodeinfo.getPort() + nodeinfo.getContextpath();
            return HttpRequest.sendPost(url,toInvoke.toJSONString());
        }catch (Exception e){
            throw e;
        }
    }
}
