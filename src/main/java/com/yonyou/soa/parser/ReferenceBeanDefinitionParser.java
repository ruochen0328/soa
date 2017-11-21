package com.yonyou.soa.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:01
 */

public class ReferenceBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> beanClass;

    public ReferenceBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition=new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(beanClass);
        rootBeanDefinition.setLazyInit(false);
        String id=element.getAttribute("id");
        String ref=element.getAttribute("interface");
        String loadbalance=element.getAttribute("loadbalance");
        String protocol=element.getAttribute("protocol");
        rootBeanDefinition.getPropertyValues().add("id",id);
        rootBeanDefinition.getPropertyValues().add("ref",ref);
        rootBeanDefinition.getPropertyValues().add("loadbalance",loadbalance);
        rootBeanDefinition.getPropertyValues().add("protocol",protocol);
        return rootBeanDefinition;
    }
}
