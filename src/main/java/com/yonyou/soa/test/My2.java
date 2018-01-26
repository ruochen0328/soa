package com.yonyou.soa.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class My2 implements BeanDefinitionRegistryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0)
			throws BeansException {
		// TODO Auto-generated method stub

	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry arg0)
			throws BeansException {
		System.out.println("BeanDefinitionRegistryPostProcessor my2");
		
		BeanDefinition bd = arg0.getBeanDefinition("jackstudent");
		MutablePropertyValues mpv = bd.getPropertyValues();
		
		mpv.addPropertyValue("username", "jack-vip");
	}

}
