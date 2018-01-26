package com.yonyou.thread.util;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/12 11:11
 */

public class CountDownLatchDemo {

    static class Producer implements Runnable{
        private CountDownLatch countDownLatch;
        private ConcurrentHashMap<String,Integer> map;
        public Producer(CountDownLatch countDownLatch,ConcurrentHashMap<String,Integer> map) {
            this.countDownLatch = countDownLatch;
            this.map=map;
        }
        @Override
        public void run() {
            Random random=new Random();
            int num=random.nextInt(1000)+1000;
            System.out.println(Thread.currentThread().getId()+"product "+num);
            map.put(Thread.currentThread().getId( )+"",num);
            countDownLatch.countDown();
        }
    }
    static class Consumer implements Runnable{
        private ConcurrentHashMap<String,Integer> map;
        private CountDownLatch countDownLatch;

        public Consumer(ConcurrentHashMap<String, Integer> map, CountDownLatch countDownLatch) {
            this.map = map;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                int sum=0;
                for (Map.Entry<String,Integer> entry:map.entrySet()){
                    sum+=entry.getValue();
                }
                System.out.println("consumer calculate sum ="+sum );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int producorThreadSize=5;
        CountDownLatch countDownLatch=new CountDownLatch(producorThreadSize);
        ConcurrentHashMap map=new ConcurrentHashMap();
        Producer producer=new Producer(countDownLatch,map);
        for (int i = 0; i < producorThreadSize; i++) {
            new Thread(producer).start();
        }
        new Thread(new Consumer(map,countDownLatch)).start();
    }
}
