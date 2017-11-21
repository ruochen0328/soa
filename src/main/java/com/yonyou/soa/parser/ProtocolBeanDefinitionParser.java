package com.yonyou.soa.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:07
 */

public class ProtocolBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> beanClass;

    public ProtocolBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition=new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(beanClass);
        rootBeanDefinition.setLazyInit(false);
        String name=element.getAttribute("name");
        String host=element.getAttribute("host");
        String port=element.getAttribute("port");
        rootBeanDefinition.getPropertyValues().add("name",name);
        rootBeanDefinition.getPropertyValues().add("host",host);
        rootBeanDefinition.getPropertyValues().add("port",port);
        return rootBeanDefinition;
    }
}
