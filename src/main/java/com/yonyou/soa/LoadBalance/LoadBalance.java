package com.yonyou.soa.LoadBalance;

import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/22 11:23
 */

public interface LoadBalance {
    NodeInfo doSelect(List<String> registryInfo);
}
