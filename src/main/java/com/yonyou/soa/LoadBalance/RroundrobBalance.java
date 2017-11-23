package com.yonyou.soa.LoadBalance;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/22 11:28
 */

public class RroundrobBalance implements LoadBalance {
    private static Integer index=0;
    public NodeInfo doSelect(List<String> registryInfo) {
        synchronized (index){
            if (index>registryInfo.size()){
                index=0;
            }
            JSONObject registryjo=JSONObject.parseObject(registryInfo.get(index));
            index++;
            Collection<Object> values = registryjo.values();
            NodeInfo nodeInfo=new NodeInfo();
            for (Object obj: values){
                JSONObject node=JSONObject.parseObject(obj.toString());
                JSONObject protocol = node.getJSONObject("protocol");
                JSONObject host = node.getJSONObject("host");
                nodeInfo.setHost(protocol.get("host")!=null? protocol.getString("host"):"");
                nodeInfo.setPort(protocol.get("port")!=null?protocol.getString("port"):"");
                nodeInfo.setContextpath(protocol.get("contextpath")!=null? protocol.getString("contextpath"):"");
            }
            return nodeInfo;
        }
    }
}
