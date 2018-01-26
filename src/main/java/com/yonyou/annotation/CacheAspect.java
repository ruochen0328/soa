package com.yonyou.annotation;

import com.yonyou.annotation.elparser.ElParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/19 9:49
 */
@Aspect
@Component
public class CacheAspect {
    @Around("execution(public * com.yonyou.annotation.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp){
        try {
            //获得形式参数名
            System.out.println("前置增强");
            Object proceed = pjp.proceed();
            System.out.println("后置增强");
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
/*    @Before("@annotation(mycache)")
    public void before(Mycache mycache){
        System.out.println("before "+mycache.key());
    }
    @After("@annotation(mycache)")
    public void after(Mycache mycache){
        System.out.println("after "+mycache.key());
    }*/
}
