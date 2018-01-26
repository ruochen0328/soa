package com.yonyou.annotation;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/20 13:39
 */
@Mycache(key="key",cacheName = "proxyDemo")
public class ProxyDemo {
    private String pr;
    private String get(Product product,String [] arg){
        return this.pr+product.getId()+arg[0];
    }
    public void set(){
        System.out.println("ceshi");
    }
    public static void main(String[] args) {
        ProxyDemo proxyDemo=new ProxyDemo();
        Class<? extends ProxyDemo> clazz = proxyDemo.getClass();
        try {
            Field pr = clazz.getDeclaredField("pr");
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().equals("get")){
                    Parameter[] parameters = methods[i].getParameters();
                    System.out.println(parameters[0].getName());
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
