package com.ruochen;

import com.ruochen.aoptest.IToAdviceClass;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/21 14:46
 */
@Component
@Aspect
public class MyAspect {
    @Before("execution(* com.ruochen.aoptest..*.*(..))")
    public String beforeAspect(JoinPoint joinPoint){
        try {
//            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
//            IToAdviceClass iToAdviceClass = (IToAdviceClass)Class.forName(signature.getDeclaringTypeName()).newInstance();
//            signature.getMethod().invoke(iToAdviceClass,"x",0);
            System.out.println("命中所有方法：前置增强");
            return "xxxx";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
    @After("execution(* com.ruochen.aoptest.IToAdviceClass.afterAdvice(..))")
    public void afterAspect(){
        System.out.println("后置增强");
    }
    @Around("execution(* com.ruochen.aoptest.IToAdviceClass.aroundAdvice(..))")
    public void aroundAspect(ProceedingJoinPoint joinPoint){
        System.out.println("环绕前置增强");
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕后置增强");
    }
}
