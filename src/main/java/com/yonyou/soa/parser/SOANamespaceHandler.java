package com.yonyou.soa.parser;

import com.yonyou.soa.configbean.Protocol;
import com.yonyou.soa.configbean.Reference;
import com.yonyou.soa.configbean.Registry;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/18 15:55
 */

public class SOANamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("registry",new RegistryBeanDefinitionParser(Registry.class));
        registerBeanDefinitionParser("reference",new ReferenceBeanDefinitionParser(Reference.class));
        registerBeanDefinitionParser("protocol",new ProtocolBeanDefinitionParser(Protocol.class));
        registerBeanDefinitionParser("service",new ServiceBeanDefinitionParser(Registry.class));
    }
}
