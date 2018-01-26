package com.yonyou.thread.threadpool;

import java.util.concurrent.*;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/12 9:52
 */

public class ThreadPoolDemo {
    private static CountDownLatch countDownLatch=new CountDownLatch(10);
    private static class Mywork implements Runnable{
        @Override
        public void run() {
            try {
                countDownLatch.await();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        BlockingQueue<Runnable> quene=new LinkedBlockingQueue<>(5);
        RejectedExecutionHandler myReject=new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r+" is rejected");
            }
        };
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,4,1, TimeUnit.SECONDS,quene,myReject);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Mywork());
            countDownLatch.countDown();
        }
        threadPoolExecutor.shutdown();
    }
}
