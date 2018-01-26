package com.yonyou.soa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring.xml")
public class MyTest implements ApplicationContextAware {
	
	public ApplicationContext context;
	
	@Test
	public void test1(){
		
		Student s = (Student)context.getBean("jackstudent");
		
		System.out.println(s.getUsername());
		System.out.println(s.getPassword());
		System.out.println(s.getSchool());
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
		
	}
	
	

}
