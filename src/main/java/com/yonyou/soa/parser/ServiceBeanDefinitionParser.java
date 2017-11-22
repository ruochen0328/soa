package com.yonyou.soa.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:15
 */

public class ServiceBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> beanClass;

    public ServiceBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition=new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(beanClass);
        rootBeanDefinition.setLazyInit(false);
        String inter=element.getAttribute("interface");
        String ref=element.getAttribute("ref");
        String protocol=element.getAttribute("protocol");
        rootBeanDefinition.getPropertyValues().add("inter",inter);
        rootBeanDefinition.getPropertyValues().add("ref",ref);
        rootBeanDefinition.getPropertyValues().add("protocol",protocol);
        parserContext.getRegistry().registerBeanDefinition(inter+ref+protocol,rootBeanDefinition);
        return rootBeanDefinition;
    }
}
