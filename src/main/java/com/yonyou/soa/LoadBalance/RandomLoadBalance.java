package com.yonyou.soa.LoadBalance;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/22 11:28
 */

public class RandomLoadBalance implements LoadBalance{
    public NodeInfo doSelect(List<String> registryInfo) {
        Random random=new Random();
        int index=random.nextInt(registryInfo.size());
        String registry = registryInfo.get(index);
        JSONObject registryjo=JSONObject.parseObject(registry);
        Collection<Object> values = registryjo.values();
        JSONObject node=new JSONObject();
        for (Object obj:values){
            node=JSONObject.parseObject(obj.toString());
        }
        JSONObject protocol = node.getJSONObject("protocol");
        NodeInfo nodeInfo=new NodeInfo();
        nodeInfo.setHost(protocol.get("host")!=null? protocol.getString("host"):"");
        nodeInfo.setPort(protocol.get("port")!=null?protocol.getString("port"):"");
        nodeInfo.setContextpath(protocol.get("contextpath")!=null? protocol.getString("contextpath"):"");
        return nodeInfo;
    }
}
