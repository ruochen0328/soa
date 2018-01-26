package com.yonyou.thread.util;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/12 13:35
 */
//五个线程去生成数据，等三个线程生成数据完成再启动一个线程去汇总结果
public class CyclicBarrierDemo {
    private static ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<String,Integer>();
    static class Producer implements Runnable{
        private CyclicBarrier cyclicBarrier;

        public Producer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            Random random=new Random();
            int num=random.nextInt(1000)+1000;
            System.out.println(Thread.currentThread().getId()+"product "+num);
            map.put(Thread.currentThread().getId( )+"",num);
            try {
                Thread.sleep(1000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    private static class Consumer implements Runnable{
        @Override
        public void run() {
            int sum=0;
            for (Map.Entry<String,Integer> entry:map.entrySet()){
                sum+=entry.getValue();
            }
            System.out.println("consumer caculate sum ="+sum);
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,new Consumer());
        Producer producer=new Producer(cyclicBarrier);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(producer);
            thread.start();
        }
    }
}
