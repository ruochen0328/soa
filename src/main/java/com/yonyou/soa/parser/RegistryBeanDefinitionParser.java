package com.yonyou.soa.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/18 15:58
 */

public class RegistryBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> beanClass;
    public RegistryBeanDefinitionParser(Class<?> beanClass){
        this.beanClass=beanClass;
    }
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition=new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(beanClass);
        rootBeanDefinition.setLazyInit(false);
        String protocol = element.getAttribute("protocol");
        String address = element.getAttribute("address");
        rootBeanDefinition.getPropertyValues().addPropertyValue("protocol",protocol);
        rootBeanDefinition.getPropertyValues().addPropertyValue("address",address);
        return rootBeanDefinition;
    }
}
