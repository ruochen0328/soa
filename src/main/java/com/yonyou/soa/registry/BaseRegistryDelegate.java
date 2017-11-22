package com.yonyou.soa.registry;

import com.yonyou.soa.configbean.Protocol;
import com.yonyou.soa.configbean.Reference;
import com.yonyou.soa.configbean.Registry;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/21 20:05
 */

public class BaseRegistryDelegate {
    public static void regist(String reference, ApplicationContext applicationContext){
        Registry registry= applicationContext.getBean(Registry.class);
        String protocol = registry.getProtocol();
        BaseRegistry baseRegistry=Registry.getRegistryMap().get(protocol);
        baseRegistry.registry(reference,applicationContext);
    }
    public static List<String> getRegistry(String id,ApplicationContext applicationContext){
        Registry registry= applicationContext.getBean(Registry.class);
        String protocol = registry.getProtocol();
        BaseRegistry baseRegistry=Registry.getRegistryMap().get(protocol);
        return baseRegistry.getRegistry(id,applicationContext);
    }
}
