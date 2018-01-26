package com.ruochen.aoptest;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/21 14:05
 */

public interface IToAdviceClass {
    public String aroundAdvice(String arg0,Integer arg1);
    public String beforeAdvice(String arg0,Integer arg1);
    public Integer afterAdvice(Integer arg0);
}
