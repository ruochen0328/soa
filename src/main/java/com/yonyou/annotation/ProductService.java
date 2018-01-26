package com.yonyou.annotation;

import org.springframework.stereotype.Service;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/19 10:58
 */
public interface ProductService {
    public String getProduct(String key,Product product);
    public void testOther();
}
