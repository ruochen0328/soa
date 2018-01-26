package com.ruochen;

import com.ruochen.aoptest.IToAdviceClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/21 14:03
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class TestAspect{
    @Autowired
    private IToAdviceClass iToAdviceClass;
    @Test
    public void test(){
        iToAdviceClass.beforeAdvice("arg0",3);
    }
    @Test
    public void after(){
        iToAdviceClass.afterAdvice(9);
    }
    @Test
    public void around(){
        iToAdviceClass.aroundAdvice("x",4);
    }
    @Test
    public void tet(){
        String end=new SimpleDateFormat("yyyy-MM-dd").format(1511971200000L);
        System.out.println(end);
    }
}