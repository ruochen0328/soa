package com.yonyou.pattern.subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/21 9:45
 */

public interface Publisher {

    public void addSubsciber(Subscriber subscriber);
    public void notifySubscriber();
}
