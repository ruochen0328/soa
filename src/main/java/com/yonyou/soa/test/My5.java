package com.yonyou.soa.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;

public class My5 implements BeanDefinitionRegistryPostProcessor, Ordered {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0)
			throws BeansException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry arg0)
			throws BeansException {
		System.out.println("BeanDefinitionRegistryPostProcessor Ordered my5");
		
		

		BeanDefinition bd = arg0.getBeanDefinition("jackstudent");
		MutablePropertyValues mpv = bd.getPropertyValues();
		
		mpv.addPropertyValue("school", "dongnao");

	}

}
