package com.pattern.builder;

/**
 * @author yangwshh@yonyou.com
 * @date 2018/1/26 10:51
 */

public class BuilderTest {
    public static void main(String[] args) {
        ClientOptions co=ClientOptions.builder().name("yang").build();
        System.out.println(co.getSex());
    }
}
