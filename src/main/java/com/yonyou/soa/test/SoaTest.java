package com.yonyou.soa.test;

import com.yonyou.soa.configbean.Reference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/20 19:22
 */

public class SoaTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("test.xml");
        TestService tes = applicationContext.getBean(TestService.class);
        tes.test();

    }
}
