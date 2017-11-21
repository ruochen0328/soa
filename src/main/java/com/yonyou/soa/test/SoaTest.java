package com.yonyou.soa.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:22
 */

public class SoaTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("test.xml");
        applicationContext.getBean("TestServiceImpl");
    }
}
