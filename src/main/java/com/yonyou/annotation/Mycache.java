package com.yonyou.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/19 9:47
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mycache {
    String key();//缓存键值

    String cacheName();//缓存名称

    String backupName() default "";//备份缓存的名字

    boolean needBloomFilter() default false;//是否需要布隆过滤器

    boolean needLock() default false;//是否加上分布式锁
}
