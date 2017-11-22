package com.yonyou.soa.registry;

import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/21 20:12
 */

public interface BaseRegistry {
    public boolean registry(String param, ApplicationContext applicationContext);
    public List<String> getRegistry(String id,ApplicationContext application);
}
