package com.yonyou.pattern.subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/21 10:57
 */

public class SchoolPublisher implements Publisher{
    List<Subscriber> subscribers=new ArrayList<Subscriber>();
    @Override
    public void addSubsciber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void notifySubscriber() {
        for(Subscriber subscriber:subscribers){
            subscriber.doSomething();
        }
    }
}
