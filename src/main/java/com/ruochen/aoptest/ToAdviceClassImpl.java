package com.ruochen.aoptest;

import org.springframework.stereotype.Service;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/21 14:03
 */
@Service
public class ToAdviceClassImpl implements IToAdviceClass{
    @Override
    public String aroundAdvice(String arg0,Integer arg1){
        System.out.println("aroundAdvice");
        return "aroundAdvice";
    }
    @Override
    public String beforeAdvice(String arg0,Integer arg1){
        System.out.println("beforeAdvice");
        return "beforeAdvice";
    }
    @Override
    public Integer afterAdvice(Integer arg0){
        System.out.println("afterAdvice");
        return 0;
    }
}
